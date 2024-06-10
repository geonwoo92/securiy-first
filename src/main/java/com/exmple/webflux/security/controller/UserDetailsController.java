package com.exmple.webflux.security.controller;

import com.exmple.webflux.security.service.UserDetailsServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Log
@Component
@RequiredArgsConstructor
public class UserDetailsController implements AuthenticationProvider {


    private final UserDetailsServiceImpl userDetailsServiceImpl;

    @Value("${jwt.secret: secret-key}")
    private String secretKey;

    @Value("${jwt.expiration: 3600000}")
    private long validityInMs = 3600000;




    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return false;
    }
}
