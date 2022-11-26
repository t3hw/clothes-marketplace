package com.ravid.clothes_marketplace.app.logic.requesthandlers.publicmarketplace.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.db.model.User;
import com.ravid.clothes_marketplace.app.db.repo.UserRepository;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class AuthenticationPOST extends RequestHandler {
    
    private static PasswordEncoder pwEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository repo;

    private AuthenticationRequestDTO req;

    @Override
    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> handleRequest(){
        
        User user = repo.getReferenceById(req.getUserId());
        String password = user.getPassword();
        // Get password from repo and match it to the one in the request
        if (pwEncoder.matches(req.getPassword(), password)){
            System.out.println("TODO THIS");
            return (ResponseEntity<T>) ResponseEntity.ok(new AuthenticationResponseDTO().token(null));
        } else {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "Username and password do not match.");
        }

    }

    // Abstract class initilization
    public AuthenticationPOST(AuthenticationRequestDTO req) {
        super(null);
        this.req = req;
    }
}
