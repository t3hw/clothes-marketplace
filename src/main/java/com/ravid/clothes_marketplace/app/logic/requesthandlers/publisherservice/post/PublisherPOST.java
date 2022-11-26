package com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.post;

import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class PublisherPOST extends RequestHandler {

    private GarmentRequestDTO req;
    private String publisherId;

    @Override
    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> handleRequest(){
        System.out.println("TODO THIS");
        
        return (ResponseEntity<T>) ResponseEntity.ok(new ClothesResponseDTO());
    }

    // Abstract class initilization
    public PublisherPOST(GarmentRequestDTO req, String publisherId) {
        super(null);
        this.req = req;
        this.publisherId = publisherId;
    }
}
