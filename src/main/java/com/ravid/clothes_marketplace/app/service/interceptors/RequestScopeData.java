package com.ravid.clothes_marketplace.app.service.interceptors;

import java.util.Optional;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class RequestScopeData {
    @Getter
    private String userId;
    @Getter
    private String httpMethod;

    private RequestScopeData(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public void verifyToken(Optional<String> bearerToken) throws HttpClientErrorException {
        bearerToken.filter(jwt -> jwt.matches("^Bearer ([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)"))
            .orElseThrow(() -> new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "JWT Missing or in an invalid format"));

        // TODO set userId
        
    }
}
