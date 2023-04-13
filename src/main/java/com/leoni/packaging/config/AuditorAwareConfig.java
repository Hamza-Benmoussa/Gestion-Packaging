package com.leoni.packaging.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
public class AuditorAwareConfig {
    @Bean
    public AuditorAware<String> auditorProvider() {
        return ()->{
//            if (SecurityContextHolder.getContext().getAuthentication() != null) {
//                    OAuth2Authentication auth = (OAuth2Authentication) SecurityContextHolder.getContext().getAuthentication();
//                    Object principal = auth.getUserAuthentication().getPrincipal();
//                    CustomUserDetails userDetails = (CustomUserDetails) principal;
//                    return userDetails.getUsername();
//                } else {
//                    return "Unknown";
//                }
                return Optional.of("Unknown");
        };
    }
}
