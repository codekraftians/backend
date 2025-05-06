package com.backend.backend.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.backend.backend.security.filter.JWTAuthenticationFilter;
import com.backend.backend.security.filter.JWTAuthorizationFilter;
import com.backend.backend.security.manager.CustomAuthenticationManager;

import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final CustomAuthenticationManager customAuthenticationManager;

    public SecurityConfig(CustomAuthenticationManager customAuthenticationManager){
        this.customAuthenticationManager = customAuthenticationManager;
    }

@Bean
public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    JWTAuthenticationFilter authenticationFilter = new JWTAuthenticationFilter(customAuthenticationManager);
    authenticationFilter.setFilterProcessesUrl("/login");

    return http
        .csrf(csrf -> csrf.disable())
        .headers(headers -> headers.frameOptions(frame -> frame.disable()))
        .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
        .authorizeHttpRequests(auth -> auth
            .requestMatchers("/h2/**", "/login", "/register").permitAll()
            .anyRequest().authenticated()
        )
        .addFilter(authenticationFilter)
        .addFilterAfter(new JWTAuthorizationFilter(), JWTAuthenticationFilter.class)
        .exceptionHandling(exceptions -> exceptions
        .authenticationEntryPoint((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            res.getWriter().write("Not authorized");
            res.getWriter().flush();
        })
        .accessDeniedHandler((req, res, ex) -> {
            res.setStatus(HttpServletResponse.SC_FORBIDDEN);
            res.getWriter().write("Access denied");
            res.getWriter().flush();
        }))
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessHandler((req, res, auth) -> {
                res.setStatus(HttpServletResponse.SC_OK);
                res.getWriter().write("Logout successful");
                res.getWriter().flush();
            })
        )
        .build();
    }

@Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
