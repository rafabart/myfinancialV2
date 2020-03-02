package com.myfinancial.model.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.myfinancial.model.domain.request.LoginRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    final private AuthenticationManager authenticationManager;

    final private JWTUtil jwtUtil;


    @Autowired
    public JWTAuthenticationFilter(final AuthenticationManager authenticationManager, final JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
    }


    @Override
    public Authentication attemptAuthentication(final HttpServletRequest request,
                                                final HttpServletResponse response) throws AuthenticationException {
        try {
            final LoginRequest loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                    loginRequest.getEmail(), loginRequest.getPassword(), new ArrayList<>());

            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    protected void successfulAuthentication(final HttpServletRequest request, HttpServletResponse response,
                                            final FilterChain chain, final Authentication authResult) {

        String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();

        String token = jwtUtil.generateToken(username);

        response.addHeader("Authorization", "Bearer " + token);
        response.addHeader("access-control-expose-headers", "Authorization");
    }
}
