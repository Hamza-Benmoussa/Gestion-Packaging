package com.leoni.packaging;

import com.leoni.packaging.enums.Role;
import com.leoni.packaging.model.*;
import com.leoni.packaging.service.*;
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

}
