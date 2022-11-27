package com.ravid.clothes_marketplace.app.service;

import java.math.BigDecimal;

import javax.validation.Valid;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.app.service.interceptors.RequestScopeData;
import com.ravid.clothes_marketplace.server.api.PublisherApiDelegate;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentResponseDTO;

@Service
public class PublisherService implements PublisherApiDelegate, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public ResponseEntity<Void> deleteGarment(BigDecimal id) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return ((RequestHandler) context.getBean("publisher"+data.getHttpMethod(), id, data.getUserId())).handleRequest();
    }

    @Override
    public ResponseEntity<GarmentResponseDTO> publishNewGarment(@Valid GarmentRequestDTO garmentRequestDTO) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return ((RequestHandler) context.getBean("publisher"+data.getHttpMethod(), garmentRequestDTO, data.getUserId())).handleRequest();
    }

    @Override
    public ResponseEntity<Void> updateGarment(BigDecimal id,
            @Valid GarmentRequestDTO garmentRequestDTO) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return ((RequestHandler) context.getBean("publisher"+data.getHttpMethod(), id, garmentRequestDTO, data.getUserId())).handleRequest();
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
