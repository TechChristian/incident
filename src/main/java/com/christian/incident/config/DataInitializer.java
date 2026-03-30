package com.christian.incident.config;

import com.christian.incident.entity.User;
import com.christian.incident.entity.enums.Roles;
import com.christian.incident.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataInitializer {
    @Bean
    CommandLineRunner initAdmin(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        return args -> {
            if (!userRepository.existsByEmail("admin@gmail.com")) {
                User admin = new User();
                admin.setUsername("admin");
                admin.setEmail("admin@gmail.com");
                admin.setPassword(passwordEncoder.encode("12345678"));
                admin.setRole(Roles.ADMIN);

                userRepository.save(admin);
            }
        };
    }
}
