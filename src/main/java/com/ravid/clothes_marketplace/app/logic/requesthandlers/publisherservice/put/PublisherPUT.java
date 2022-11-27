package com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.put;

import java.math.BigDecimal;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.db.model.Garment;
import com.ravid.clothes_marketplace.app.db.repo.GarmentRepository;
import com.ravid.clothes_marketplace.app.db.repo.PublisherRepository;
import com.ravid.clothes_marketplace.app.errors.UserException;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;

@Component
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class PublisherPUT extends RequestHandler {

    private BigDecimal garmentId;
    private GarmentRequestDTO req;
    private String publisherId;
    @Autowired private PublisherRepository pubRepo;
    @Autowired private GarmentRepository garmentRepo;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public <T> ResponseEntity<T> handleRequest(){
        // Find garment - filter by garments that belong to the authenticated user
        Garment garment = pubRepo.getReferenceById(publisherId).getGarments().stream().filter(garm -> garm.getId().equals(garmentId)).findFirst()
            .orElseThrow(() -> new UserException("Garment not found", new HttpClientErrorException(HttpStatus.NOT_FOUND)));
        
        garmentRepo.updateGarmByGarmId(garment.getId(), req.getGarmentType().getValue(),
                                       req.getGarmentSize().getValue(), req.getGarmentDescription(),
                                       req.getPrice());
        
        return (ResponseEntity<T>) ResponseEntity.noContent().build();
    }

    // Abstract class initilization
    public PublisherPUT(BigDecimal garmentId, GarmentRequestDTO req, String publisherId) {
        super(null);
        this.garmentId = garmentId;
        this.req = req;
        this.publisherId = publisherId;
    }
}
