package org.example.demo1.service.auth;

import org.example.demo1.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserDetailsService userDetailsService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(UserDetailsService userDetailsService,
                                     JwtTokenProvider jwtTokenProvider,
                                     AuthenticationManager authenticationManager) {
        this.userDetailsService = userDetailsService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Override
    @Transactional(readOnly = true)
    public String login(String username, String password) {
        this.authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                username, password
        ));

        UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
        return this.jwtTokenProvider.generateToken(userDetails);
    }
}