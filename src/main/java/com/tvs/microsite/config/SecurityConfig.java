package com.tvs.microsite.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Disable for testing (enable in production)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/add-user.html").permitAll()
                        .requestMatchers("/view-users.html").permitAll()
                        .requestMatchers("/api/users").permitAll() // Allow API access
                        .anyRequest().authenticated()
                )
                .formLogin();
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withDefaultPasswordEncoder()
                .username("admin")
                .password("password") // Use a stronger password in production!
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(user);
    }
}