package com.ravid.clothes_marketplace.app.service.interceptors;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import lombok.Getter;
import lombok.Setter;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class RequestScopeData {
    @Getter @Setter
    private String userId;
    @Getter
    private String httpMethod;

    private RequestScopeData(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    // public void getTokenData(Optional<String> bearerToken) throws HttpClientErrorException {
    //     bearerToken.filter(jwt -> jwt.matches("^Bearer ([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_=]+)\\.([a-zA-Z0-9_\\-\\+\\/=]*)"))
    //         .orElseThrow(() -> new UserException((HttpStatus.UNAUTHORIZED, "JWT Missing or in an invalid format"));
    // }
}
