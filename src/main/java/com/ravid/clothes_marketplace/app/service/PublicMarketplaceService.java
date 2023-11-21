package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.publicmarketplace.query.Clothes;
import com.ravid.clothes_marketplace.server.api.MarketplaceApiDelegate;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;

import jakarta.validation.constraints.Size;

import java.util.Optional;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicMarketplaceService implements MarketplaceApiDelegate, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public ResponseEntity<ClothesResponseDTO> getClothes(Optional<String> sellerName, Optional<@Size(max = 9) String> sellerId, 
                                                        Optional<String> garmentType, Optional<Float> minPrice,
                                                        Optional<Float> maxPrice, Optional<String> size) {
        return context.getBean(Clothes.class ,sellerName,sellerId,garmentType,minPrice,maxPrice,size).handleRequest();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
