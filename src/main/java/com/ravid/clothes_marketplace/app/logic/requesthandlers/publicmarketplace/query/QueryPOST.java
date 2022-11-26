package com.ravid.clothes_marketplace.app.logic.requesthandlers.publicmarketplace.query;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.db.repo.GarmentRepository;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class QueryPOST extends RequestHandler {

    @Autowired
    GarmentRepository repo;

    private Optional<String> publisherNamePattern;
    private Optional<String> publisherId;
    private Optional<String> type;
    private Optional<String> size;
    private Optional<Float>  minPrice ;
    private Optional<Float>  maxPrice;

    @Override
    @SuppressWarnings("unchecked")
    public <T> ResponseEntity<T> handleRequest(){
        repo.findGarmentsBySearchParams(publisherNamePattern,
                                        publisherId,
                                        type,
                                        size,
                                        minPrice,
                                        maxPrice);
        
        
        
        return (ResponseEntity<T>) ResponseEntity.ok(new ClothesResponseDTO());
    }

    // Abstract class initilization
    public QueryPOST(Optional<String> publisherNamePattern,
                     Optional<String> publisherId,
                     Optional<String> type,
                     Optional<String> size,
                     Optional<Float>  minPrice,
                     Optional<Float>  maxPrice) {
        super(null);
        this.publisherNamePattern = publisherNamePattern;
        this.publisherId = publisherId;
        this.type = type;
        this.size = size;
        this.minPrice  = minPrice ;
        this.maxPrice = maxPrice;
    }
}
