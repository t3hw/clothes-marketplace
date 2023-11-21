package com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.put;

import jakarta.transaction.Transactional;

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
import com.ravid.clothes_marketplace.server.model.GarmentPUTRequestDTO;

// For Dynamic handling
@Component("publisher.updateGarment")
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class UpdateGarment extends RequestHandler {

    private Integer garmentId;
    private GarmentPUTRequestDTO req;
    private String publisherId;
    @Autowired private PublisherRepository pubRepo;
    @Autowired private GarmentRepository garmentRepo;

    /* 
     * Request handler for update garment endpoint
     */
    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public <T> ResponseEntity<T> handleRequest(){
        // Find garment - filter by garments that belong to the authenticated user
        Garment garment = pubRepo.getReferenceById(publisherId).getGarments().stream().filter(garm -> garm.getId().equals(garmentId)).findFirst()
            .orElseThrow(() -> new UserException("Garment not found", new HttpClientErrorException(HttpStatus.NOT_FOUND)));
        
        
        // Check request validity
        if (req.getGarmentType() == null)
            throw new UserException("MISSING MANDATORY PARAM OR UNDEFINED ENUM VALUE: garmentType", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        if (req.getGarmentSize() == null)
            throw new UserException("MISSING MANDATORY PARAM OR UNDEFINED ENUM VALUE: garmentSize", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        if (req.getPrice() == null)
            throw new UserException("MISSING MANDATORY PARAM: price", new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        garment.setGarmentType(req.getGarmentType().getValue());
        garment.setSize(req.getGarmentSize().getValue());
        garment.setDescription(req.getGarmentDescription());
        garment.setPrice(req.getPrice());
        garmentRepo.save(garment);
        
        return (ResponseEntity<T>) ResponseEntity.noContent().build();
    }

    public UpdateGarment(Integer garmentId, GarmentPUTRequestDTO req, String publisherId) {
        this.garmentId = garmentId;
        this.req = req;
        this.publisherId = publisherId;
    }
}
