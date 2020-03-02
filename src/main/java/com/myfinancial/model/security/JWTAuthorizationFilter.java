package com.myfinancial.model.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

    final private JWTUtil jwtUtil;

    final private UserDetailsService userDetailsService;


    @Autowired
    public JWTAuthorizationFilter(final AuthenticationManager authenticationManager, final JWTUtil jwtUtil,
                                  final UserDetailsService userDetailsService) {
        super(authenticationManager);
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }


    @Override
    protected void doFilterInternal(final HttpServletRequest request, final HttpServletResponse response, final FilterChain chain) throws IOException, ServletException {

        final String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {

            final UsernamePasswordAuthenticationToken authenticationToken = getAuthentication(header.substring(7));

            if (authenticationToken != null) {
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        chain.doFilter(request, response);
    }


    private UsernamePasswordAuthenticationToken getAuthentication(final String token) {

        if (jwtUtil.validToken(token)) {

            final String username = jwtUtil.getUsername(token);

            final UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            return new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        }

        return null;
    }
}
