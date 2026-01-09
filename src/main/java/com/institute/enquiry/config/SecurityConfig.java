package com.institute.enquiry.config;

import com.institute.enquiry.repository.AdminRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AdminRepository adminRepository;

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> adminRepository.findByUsername(username)
                .map(admin -> User.withUsername(admin.getUsername())
                        .password(admin.getPassword())
                        .roles("ADMIN")
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(null);
        provider.setUserDetailsPasswordService((UserDetailsPasswordService) userDetailsService());
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
            .csrf(csrf -> csrf.disable())

            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/",
                        "/courses",
                        "/course/**",
                        "/enquiry/**",
                        "/about",
                        "/css/**",
                        "/js/**",
                        "/images/**",
                        "/brochures/**"
                ).permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .anyRequest().authenticated()
            )

            .formLogin(login -> login
                .loginPage("/admin/login")
                .loginProcessingUrl("/admin/login")
                .defaultSuccessUrl("/admin/dashboard", true)
                .permitAll()
            )

            .logout(logout -> logout
                .logoutUrl("/admin/logout")
                .logoutSuccessUrl("/admin/login?logout")
                .permitAll()
            );

        return http.build();
    }
}
