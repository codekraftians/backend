package com.backend.backend.security.filter;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.backend.backend.model.User;
import com.backend.backend.security.manager.CustomAuthenticationManager;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import io.github.cdimascio.dotenv.Dotenv;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final CustomAuthenticationManager customAuthenticationManager;

    public JWTAuthenticationFilter(CustomAuthenticationManager customAuthenticationManager){
        this.customAuthenticationManager = customAuthenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException {
        try {
            User user = new ObjectMapper().readValue(request.getInputStream(), User.class);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword());
            return customAuthenticationManager.authenticate(authentication);
        } catch (IOException e) {
            throw new RuntimeException("Error while reading user credentials", e);
        }
    }

    @Override
        protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
            Authentication authResult) throws IOException, ServletException {

        Dotenv dotenv = Dotenv.load();
        String secret = dotenv.get("JWT_SECRET");

        String token = JWT.create()
                .withSubject(authResult.getName())
                .withExpiresAt(new Date(System.currentTimeMillis() + 864_000_000))
                .sign(Algorithm.HMAC512(secret));

        response.setContentType("application/json");
        response.getWriter().write("{\"token\": \"" + token + "\"}");
        response.getWriter().flush();
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
            AuthenticationException failed) throws IOException, ServletException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("{\"error\": \"Wrong credentials\"}");
        response.getWriter().flush();
    }
}
