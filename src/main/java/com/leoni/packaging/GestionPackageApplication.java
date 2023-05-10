package com.leoni.packaging;

import com.leoni.packaging.dao.CableRepository;
import com.leoni.packaging.dao.PackageRepository;
import com.leoni.packaging.enums.CableType;
import com.leoni.packaging.enums.Role;
import com.leoni.packaging.enums.Steering;
import com.leoni.packaging.model.*;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.service.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GestionPackageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionPackageApplication.class, args);
	}

	@Bean
	CommandLineRunner runner (UserService userService){
		return args ->{
            try{
                userService.findUserByUsername("ekko");
            }catch (Exception e){
                userService.addUser(AppUser.builder()
                        .name("anas")
                        .login("ekko")
                        .role(Role.ADMIN)
                        .build());
            }
		};
	}

	//@Bean @Transactional
	CommandLineRunner runner(RouteService routeService,
							 LineService lineService,
							 GroupService groupService,
							 UserService userService,
							 PackageRepository packageRepository,
							 CableRepository cableRepository,
							 StatisticService statisticService
							 ){
		return args -> {

			Group group1 = groupService.addGroup(Group.builder().name("group1").build());
			Group group2 = groupService.addGroup(Group.builder().name("group2").build());
			Group group3 = groupService.addGroup(Group.builder().name("group3").build());

			Route route1 = routeService.addRoute(Route.builder().routeV("route1").build());
			Route route2 = routeService.addRoute(Route.builder().routeV("route2").build());
			Route route3 = routeService.addRoute(Route.builder().routeV("route3").build());

			Line line1 = lineService.addLine(Line.builder()
					.lineName("line1")
					.capacity(150)
					.route(route1)
					.type(CableType.OV)
					.steering(Steering.LL)
					.build());
			Line line2 = lineService.addLine(Line.builder()
					.lineName("line2")
					.capacity(150)
					.route(route1)
					.type(CableType.OV)
					.steering(Steering.LL)
					.build());
			Line line3 = lineService.addLine(Line.builder()
					.lineName("line3")
					.capacity(150)
					.route(route1)
					.type(CableType.OV)
					.steering(Steering.LL)
					.build());

			AppUser anas = userService.addUser(AppUser.builder()
					.name("anas")
					.login("ekko")
					.group(group1)
					.role(Role.ADMIN)
					.build());
			AppUser hamza = userService.addUser(AppUser.builder()
					.name("hamza")
					.login("hamza")
					.group(group2)
					.role(Role.USER)
					.build());

			for(int i=0;i<100;i++){
				Line line = Arrays.asList(line1, line2, line3).get(new Random().nextInt(0, 2));
				Route route = Arrays.asList(route1, route2, route3).get(new Random().nextInt(0, 2));
				Package aPackage = Package.builder()
						.barCode(UUID.randomUUID().toString())
						.packageName("PACK"+(i+1))
						.supplier(Supplier.builder().supplierCode(UUID.randomUUID().toString()).build())
						.totalQuantity(new Random().nextInt(0,20))
						.dateDebut(LocalDateTime.now())
						.dateFin(LocalDateTime.now().plusMinutes(2))
						.state("OK")
//						.line(Arrays.asList(line1,line2, line3).get(new Random().nextInt(0,3)))
//						.route(Arrays.asList(route1,route2, route3).get(new Random().nextInt(0,3)))
						.build();
				packageRepository.save(aPackage);

			}

			packageRepository.findAll().forEach(aPackage -> {
				String steering = List.of("LL", "RL").get(new Random().nextInt(0, 1));
				String type = List.of("OV", "OV5", "OV1", "OV2").get(new Random().nextInt(0, 3));
				//Line line = Arrays.asList(line1, line2, line3).get(new Random().nextInt(0, 2));
				Route route = Arrays.asList(route1, route2, route3).get(new Random().nextInt(0, 2));
				int qte = Math.round(aPackage.getTotalQuantity() / 3);

				for(int k=0;k<3;k++){
					Line line = Arrays.asList(line1, line2, line3).get(k);
					for (int i=1;i<=qte;i++){
						Cable cable = Cable.builder()
								.barCode(UUID.randomUUID().toString())
								.aPackage(aPackage)
								.line(line)
								.started(LocalDateTime.now())
								.completed(LocalDateTime.now().plusSeconds(new Random().nextInt(1,100)))
								.build();
						cable.setDuration((int) Duration.between(cable.getStarted(), cable.getCompleted()).getSeconds());
						cableRepository.save(cable);
						aPackage.setCurrentQuatity(i);
					}
					packageRepository.save(aPackage);
				}
			});
			statisticService.findCablesByRoute(null).forEach(System.out::println);
		};
	}

}
