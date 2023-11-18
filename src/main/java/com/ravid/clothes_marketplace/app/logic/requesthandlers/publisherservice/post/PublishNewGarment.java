package com.ravid.clothes_marketplace.app.logic.requesthandlers.publisherservice.post;

import java.util.Optional;

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
import com.ravid.clothes_marketplace.server.model.GarmentPOSTRequestDTO;
import com.ravid.clothes_marketplace.server.model.GarmentResponseDTO;


// For Dynamic handling
@Component("publisher.publishNewGarment")
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class PublishNewGarment extends RequestHandler {

    private GarmentPOSTRequestDTO req;
    private String publisherId;
    @Autowired private PublisherRepository pubRepo;
    @Autowired private GarmentRepository garmentRepo;

    @Override
    @SuppressWarnings("unchecked")
    @Transactional
    public <T> ResponseEntity<T> handleRequest(){

        // Check request validity
        if (req.getGarmentType() == null)
            throw new UserException("MISSING MANDATORY PARAM OR UNDEFINED ENUM VALUE: garmentType", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        if (req.getGarmentSize() == null)
            throw new UserException("MISSING MANDATORY PARAM OR UNDEFINED ENUM VALUE: garmentSize", new HttpClientErrorException(HttpStatus.BAD_REQUEST));
        if (req.getPrice() == null)
            throw new UserException("MISSING MANDATORY PARAM: price", new HttpClientErrorException(HttpStatus.BAD_REQUEST));

        // Populate garment properties and save to repository
        Garment garment = new Garment(req.getGarmentType().getValue(), req.getGarmentSize().getValue(), req.getPrice());
        Optional.ofNullable(req.getGarmentDescription()).ifPresent(desc ->
                garment.setDescription(desc));
        garment.setPublisher(pubRepo.getReferenceById(publisherId));
        garmentRepo.save(garment);
        
        return (ResponseEntity<T>) ResponseEntity.ok(new GarmentResponseDTO().garmentId(garment.getId()));
    }

    // Abstract class initilization
    public PublishNewGarment(GarmentPOSTRequestDTO req, String publisherId) {
        super(null);
        this.req = req;
        this.publisherId = publisherId;
    }
}
