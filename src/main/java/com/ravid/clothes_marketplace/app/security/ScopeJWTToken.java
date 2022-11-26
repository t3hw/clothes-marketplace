package com.ravid.clothes_marketplace.app.security;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class ScopeJWTToken {
    private Optional<String> bearerToken;

    private ScopeJWTToken(Optional<String> bearerToken) {
        this.bearerToken = bearerToken;
    }

    public void verifyToken() throws HttpClientErrorException {
        bearerToken.filter(jwt -> jwt.matches("^Bearer ([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)"))
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "JWT Missing or in an invalid format"));
        
    }
}
