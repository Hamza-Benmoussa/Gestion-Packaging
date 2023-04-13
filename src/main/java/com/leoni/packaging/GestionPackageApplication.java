package com.leoni.packaging;

import com.leoni.packaging.dao.UserRepository;
import com.leoni.packaging.enums.Role;
import com.leoni.packaging.model.AppUser;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GestionPackageApplication {

	public static void main(String[] args) {
		SpringApplication.run(GestionPackageApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository){
		return args -> {
			userRepository.save(AppUser.builder()
							.name("anas")
							.role(Role.ADMIN)
							.login("ekkostic")
							.password("1234")
					.build());

			userRepository.findAll().forEach(System.out::println);
		};
	}
}
