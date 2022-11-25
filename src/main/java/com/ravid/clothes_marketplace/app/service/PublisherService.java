package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.server.api.PublisherServiceApi;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;
import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;





public class PublisherService implements PublisherServiceApi {
    @Value("${DbUrl}")
    String test;

    @Override
    public ResponseEntity<Void> deleteGarment(BigDecimal id) {
        // TODO Auto-generated method stub
        return PublisherServiceApi.super.deleteGarment(id);
    }

    @Override
    public ResponseEntity<Void> publishNewGarment(@Valid GarmentRequestDTO garmentRequestDTO) {
        // TODO Auto-generated method stub
        return PublisherServiceApi.super.publishNewGarment(garmentRequestDTO);
    }

    @Override
    public ResponseEntity<Void> updateGarment(BigDecimal id, @Valid GarmentRequestDTO garmentRequestDTO) {
        // TODO Auto-generated method stub
        return PublisherServiceApi.super.updateGarment(id, garmentRequestDTO);
    }


}
