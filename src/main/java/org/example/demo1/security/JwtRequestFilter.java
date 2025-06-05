package org.example.demo1.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    private static final Logger logger = LoggerFactory.getLogger(JwtRequestFilter.class);

    private static final String BEARER_PREFIX = "Bearer ";
    private static final String HEADER_NAME = "Authorization";

    private final JwtTokenProvider jwtTokenProvider;
    private final UserDetailsService userDetailService;


    public JwtRequestFilter(JwtTokenProvider jwtTokenProvider, UserDetailsService userDetailService) {
        this.jwtTokenProvider = jwtTokenProvider;
        this.userDetailService = userDetailService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        final String authHeader = request.getHeader(HEADER_NAME);
        final String requestUri = request.getRequestURI();

        logger.debug("Processing authentication for request: {}.", requestUri);

        if (!StringUtils.hasLength(authHeader) || !StringUtils.startsWithIgnoreCase(authHeader, BEARER_PREFIX)) {
            logger.debug("No JWT token found in request headers for {}.", requestUri);
            filterChain.doFilter(request, response);
            return;
        }

        try {

            final String jwt = authHeader.substring(BEARER_PREFIX.length());
            final String username = this.jwtTokenProvider.extractUsername(jwt);

            if (StringUtils.hasLength(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
                logger.debug("Authenticating user {}.", username);

                UserDetails userDetails = this.userDetailService.loadUserByUsername(username);

                if (this.jwtTokenProvider.isTokenValid(jwt, userDetails)) {
                    logger.debug("JWT token is valid for user: {}.", username);

                    SecurityContext context = SecurityContextHolder.createEmptyContext();

                    UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            userDetails.getAuthorities()
                    );

                    authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    context.setAuthentication(authToken);
                    SecurityContextHolder.setContext(context);

                    logger.info("Successfully authenticated user: {} for request {}.", username, requestUri);
                } else {
                    logger.warn("JWT token is invalid for user: {}.", username);
                }
            }
            filterChain.doFilter(request, response);
        } catch (AuthenticationException e) {
            logger.error("Authentication failed for request {}.", requestUri, e);
            throw new ServletException(e);
        }
    }
}