package com.ravid.clothes_marketplace.app.logic.requesthandlers.publicmarketplace.authentication;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class AuthenticationPOST extends RequestHandler {

    private AuthenticationRequestDTO req;

    @Override
    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> handleRequest(){
        System.out.println("TODO THIS");
        
        return (ResponseEntity<T>) ResponseEntity.ok(new ClothesResponseDTO());
    }

    // Abstract class initilization
    public AuthenticationPOST(AuthenticationRequestDTO req) {
        super(null);
        this.req = req;
    }
}
