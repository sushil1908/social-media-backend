package com.example.socialmedia.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/ping", "/h2-console/**", "/api/v1/auth/register", "/api/v1/auth/login", "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll() // Allow public access to ping, H2 console, auth, and Swagger
                .anyRequest().authenticated()
            )
            .formLogin(withDefaults())
            .csrf(csrf -> csrf.disable()) // Disable CSRF for H2 and testing
            .headers(headers -> headers.frameOptions().disable()); // Disable X-Frame-Options for H2 console

        return http.build();
    }
}
