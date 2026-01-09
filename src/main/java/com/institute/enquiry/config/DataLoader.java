package com.institute.enquiry.config;

import com.institute.enquiry.entity.Admin;
import com.institute.enquiry.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class DataLoader {

    private final AdminRepository adminRepository;

    @Bean
    CommandLineRunner loadAdmin() {
        return args -> {
            if (adminRepository.findByUsername("admin").isEmpty()) {
                Admin admin = new Admin();
                admin.setUsername("admin");
                admin.setPassword(new BCryptPasswordEncoder().encode("admin123"));
                adminRepository.save(admin);
            }
        };
    }
}
