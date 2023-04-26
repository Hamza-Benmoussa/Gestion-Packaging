package com.leoni.packaging;

import com.leoni.packaging.dao.CableRepository;
import com.leoni.packaging.dao.PackageRepository;
import com.leoni.packaging.enums.Role;
import com.leoni.packaging.model.*;
import com.leoni.packaging.model.Package;
import com.leoni.packaging.service.GroupService;
import com.leoni.packaging.service.LineService;
import com.leoni.packaging.service.RouteService;
import com.leoni.packaging.service.UserService;
import jakarta.transaction.Transactional;
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

	@Bean @Transactional
	CommandLineRunner runner(RouteService routeService,
							 LineService lineService,
							 GroupService groupService,
							 UserService userService,
							 PackageRepository packageRepository,
							 CableRepository cableRepository){
		return args -> {

			Group group1 = groupService.addGroup(Group.builder().name("group1").build());
			Group group2 = groupService.addGroup(Group.builder().name("group2").build());
			Group group3 = groupService.addGroup(Group.builder().name("group3").build());

			Route route1 = routeService.addRoute(Route.builder().routeV("route1").build());
			Route route2 = routeService.addRoute(Route.builder().routeV("route2").build());
			Route route3 = routeService.addRoute(Route.builder().routeV("route3").build());

			Line line1 = lineService.addLine(Line.builder().lineV("line1").capacity(150).build());
			Line line2 = lineService.addLine(Line.builder().lineV("line2").capacity(150).build());
			Line line3 = lineService.addLine(Line.builder().lineV("line3").capacity(150).build());

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
				Package aPackage = Package.builder()
						.barCode(UUID.randomUUID().toString())
						.supplier(Supplier.builder().supplierCode(UUID.randomUUID().toString()).build())
						.totalQuantity(new Random().nextInt(0,20))
						.dateDebut(LocalDateTime.now())
						.dateFin(LocalDateTime.now().plusMinutes(2))
						.state("OK")
						.line(Arrays.asList(line1,line2, line3).get(new Random().nextInt(0,3)))
						.route(Arrays.asList(route1,route2, route3).get(new Random().nextInt(0,3)))
						.build();
				packageRepository.save(aPackage);
			}

			packageRepository.findAll().forEach(aPackage -> {
				String steering = List.of("LL", "RL").get(new Random().nextInt(0, 1));
				String type = List.of("OV", "OV5", "OV1", "OV2").get(new Random().nextInt(0, 3));
				for (int i=1;i<=aPackage.getTotalQuantity();i++){
					Cable cable = Cable.builder()
							.barCode(UUID.randomUUID().toString())
							.steering(steering)
							.type(type)
							.aPackage(aPackage)
							.line(aPackage.getLine())
							.route(aPackage.getRoute())
							.started(LocalDateTime.now())
							.completed(LocalDateTime.now().plusSeconds(new Random().nextInt(1,100)))
							.build();
					cable.setDuration((int) Duration.between(cable.getStarted(), cable.getCompleted()).getSeconds());
					cableRepository.save(cable);
					aPackage.setCurrentQuatity(i);
				}
				packageRepository.save(aPackage);
			});
		};
	}

}
