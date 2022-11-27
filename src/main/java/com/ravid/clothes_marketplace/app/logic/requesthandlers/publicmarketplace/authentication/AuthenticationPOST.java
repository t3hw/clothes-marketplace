package com.ravid.clothes_marketplace.app.logic.requesthandlers.publicmarketplace.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.app.security.JwtUtil;
import com.ravid.clothes_marketplace.app.security.JwtUserDetailsService;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;

import lombok.SneakyThrows;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class AuthenticationPOST extends RequestHandler {
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private JwtUtil jwtUtil;
	@Autowired
	private JwtUserDetailsService userDetailsService;
    
    private AuthenticationRequestDTO req;

    @Override
    @SuppressWarnings("unchecked")
    @SneakyThrows
    public <T> ResponseEntity<T> handleRequest(){
        
        authenticate(req.getUserId(), req.getPassword());

		final UserDetails userDetails = userDetailsService
				.loadUserByUsername(req.getUserId());

		final String token = jwtUtil.generateToken(userDetails);

        return (ResponseEntity<T>) ResponseEntity.ok(new AuthenticationResponseDTO().token(token));

        // User user = repo.getReferenceById(req.getUserId());
        // String password = user.getPassword();
        // // Get password from repo and match it to the one in the request
        // if (pwEncoder.matches(req.getPassword(), password)){
        //     System.out.println("TODO THIS");
            
        // } else {
        //     throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Username and password do not match.");
        // }
    }

	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new HttpClientErrorException( HttpStatus.UNAUTHORIZED, "USER_DISABLED " + e.getMessage());
		} catch (BadCredentialsException e) {
			throw new HttpClientErrorException( HttpStatus.UNAUTHORIZED, "INVALID_CREDENTIALS " + e.getMessage());
		}
	}

    // Abstract class initilization
    public AuthenticationPOST(AuthenticationRequestDTO req) {
        super(null);
        this.req = req;
    }
}