package com.ravid.clothes_marketplace.app.security;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.ravid.clothes_marketplace.app.errors.UserException;
import com.ravid.clothes_marketplace.app.properties.MarketplaceProperties;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUserDetailsService jwtUserDetailsService;
	@Autowired
	private JwtUtil jwtUtil;
    @Autowired
    private MarketplaceProperties props;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {

		DecodedJWT token = null;
        
        if (Optional.of(request.getRequestURI()).filter(props.getAuthRequestFilter()).isPresent()) {
            final String requestTokenHeader = request.getHeader("Authorization");
            // JWT Token is in the form "Bearer token". Remove Bearer word and get
            // only the Token
            if (requestTokenHeader != null && requestTokenHeader.startsWith("Bearer ")) {
                try {
                    token = jwtUtil.decodeToken(requestTokenHeader.substring(7));
                } catch (IllegalArgumentException e) {
                    throw new UserException("Unable to get JWT Token", new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
                }
            } else {
                throw new UserException("JWT Token does not begin with Bearer String", new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
            }

            // Once we get the token validate it.
            if (token != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UserDetails userDetails = this.jwtUserDetailsService.loadUserByUsername(token.getSubject());

                // if token is valid configure Spring Security to manually set
                // authentication
                if (jwtUtil.validateToken(token, userDetails)) {

                    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                            userDetails, null, userDetails.getAuthorities());
                    usernamePasswordAuthenticationToken
                            .setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    // After setting the Authentication in the context, we specify
                    // that the current user is authenticated. So it passes the
                    // Spring Security Configurations successfully.
                    SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                } else {
                    throw new UserException("Jwt Token Invalid", new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
                }
            } else if (token != null && token.getSubject() == null) {
                throw new UserException("Error getting username from token", new HttpClientErrorException(HttpStatus.UNAUTHORIZED));
            }
        }
		chain.doFilter(request, response);
	}

}