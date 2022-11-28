package com.ravid.clothes_marketplace.app.interceptors;

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
    private String requestUri;

    private RequestScopeData(String requestUri) {
        this.requestUri = requestUri;
    }

    // Operation for decoupling of controller and logic
    public String getOperationName(){
        String temp = requestUri.replaceFirst("/", "");
        int i = temp.indexOf("/");
        temp = temp.substring(i+1);
        i = temp.indexOf("/");
        if (i == -1)
            i = temp.indexOf("?");
        if (i != -1)
            temp = temp.substring(0, i);
        return temp;
    }

    // For more complex name generation
    public String getControllerName(){
        String temp = requestUri.replaceFirst("/", "");
        int i = temp.indexOf("/");
        if (i == -1)
            i = temp.indexOf("?");
        if (i == -1)
            return temp;
        temp = temp.substring(0,i) + ".";    
        return temp;
    }
}
