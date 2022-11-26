package com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.delete;

import java.math.BigDecimal;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class PublisherDELETE extends RequestHandler {

    private BigDecimal garmentId;
    private String publisherId;

    @Override
    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> handleRequest(){
        System.out.println("TODO THIS");
        
        return (ResponseEntity<T>) ResponseEntity.noContent().build();
    }

    // Abstract class initilization
    public PublisherDELETE(BigDecimal garmentId, String publisherId) {
        super(null);
        this.garmentId = garmentId;
        this.publisherId = publisherId;
    }
}
