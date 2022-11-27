package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.app.service.interceptors.RequestScopeData;
import com.ravid.clothes_marketplace.server.api.MarketplaceApiDelegate;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicMarketplaceService implements MarketplaceApiDelegate, ApplicationContextAware {
    private ApplicationContext context;

    @Override
    public ResponseEntity<ClothesResponseDTO> getClothes(String sellerName, String sellerId
                                                        ,String garmentType, Float minPrice
                                                        ,Float maxPrice, String size) {
        Object[] args = {sellerName,sellerId,garmentType,minPrice,maxPrice,size};
        String httpMethod = context.getBean(RequestScopeData.class).getHttpMethod();
        return ((RequestHandler) context.getBean("query"+httpMethod ,args)).handleRequest();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
