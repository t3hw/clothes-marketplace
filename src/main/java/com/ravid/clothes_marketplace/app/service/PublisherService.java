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
import com.ravid.clothes_marketplace.server.api.PublisherServiceApiDelegate;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentResponseDTO;

@Service
public class PublisherService implements PublisherServiceApiDelegate, ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public ResponseEntity<Void> deleteGarment(BigDecimal id) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return handleRequest(id, data);
    }

    @Override
    public ResponseEntity<GarmentResponseDTO> publishNewGarment(@Valid GarmentRequestDTO garmentRequestDTO) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return handleRequest(garmentRequestDTO, data);
    }

    @Override
    public ResponseEntity<Void> updateGarment(BigDecimal id,
            @Valid GarmentRequestDTO garmentRequestDTO) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return handleRequest(id, data);
    }
    
    private <T> ResponseEntity<T> handleRequest(Object arg, RequestScopeData data) {
        Object[] args = {arg,data.getUserId()};
        return ((RequestHandler) context.getBean("publisher"+data.getHttpMethod() ,args)).handleRequest();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
