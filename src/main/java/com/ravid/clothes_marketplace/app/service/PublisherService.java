package com.ravid.clothes_marketplace.app.service;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.ravid.clothes_marketplace.app.interceptors.RequestScopeData;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.delete.DeleteGarment;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.post.PublishNewGarment;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.put.UpdateGarment;
import com.ravid.clothes_marketplace.server.api.PublisherApiDelegate;
import com.ravid.clothes_marketplace.server.model.GarmentPOSTRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentPUTRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentResponseDTO;

@Service
public class PublisherService implements PublisherApiDelegate, ApplicationContextAware {

    private ApplicationContext context;

    // Dynamically injecting request handlers for our methods

    @Override
    public ResponseEntity<Void> deleteGarment(Integer id) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return context.getBean(DeleteGarment.class, id, data.getUserId()).handleRequest();
    }

    @Override
    public ResponseEntity<GarmentResponseDTO> publishNewGarment(GarmentPOSTRequestDTO garmentRequestDTO) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return context.getBean(PublishNewGarment.class, garmentRequestDTO, data.getUserId()).handleRequest();
    }

    @Override
    public ResponseEntity<Void> updateGarment(Integer id, GarmentPUTRequestDTO garmentRequestDTO) {
        RequestScopeData data = context.getBean(RequestScopeData.class);
        return context.getBean(UpdateGarment.class, id, garmentRequestDTO, data.getUserId()).handleRequest();
    }
    
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }
}
