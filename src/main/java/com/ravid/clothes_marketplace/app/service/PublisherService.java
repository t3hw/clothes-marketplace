package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.server.api.PublisherServiceApiDelegate;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;
import java.math.BigDecimal;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublisherService implements PublisherServiceApiDelegate {

    @Override
    public ResponseEntity<Void> deleteGarment(BigDecimal id) {
        // TODO Auto-generated method stub
        System.out.println("hello");
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> publishNewGarment(@Valid GarmentRequestDTO garmentRequestDTO) {
        // TODO Auto-generated method stub
        System.out.println("hello");
        return ResponseEntity.noContent().build();
    }

    @Override
    public ResponseEntity<Void> updateGarment(BigDecimal id,
            @Valid GarmentRequestDTO garmentRequestDTO) {
        // TODO Auto-generated method stub
        System.out.println("hello");
        return ResponseEntity.noContent().build();
    }


}
