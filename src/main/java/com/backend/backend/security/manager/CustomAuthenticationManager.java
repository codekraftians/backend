package com.backend.backend.security.manager;

import java.util.Collections;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.backend.backend.model.User;
import com.backend.backend.service.UserService;

@Component
public class CustomAuthenticationManager implements AuthenticationManager {

    private final UserService userService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomAuthenticationManager(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder){
        this.userService = userService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        User user = userService.getUserByEmail(email);
        if (user == null || !bCryptPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Email or password is incorrect");
        }

        return new UsernamePasswordAuthenticationToken(email, user.getPassword(),
                Collections.singletonList(new SimpleGrantedAuthority("USER")));
    }
}


