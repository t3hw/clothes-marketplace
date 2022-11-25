package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.server.api.ClothesServiceApi;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;
import com.ravid.clothes_marketplace.server.model.GarmentRequestDTO;
import java.math.BigDecimal;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;





public class ClothesService implements ClothesServiceApi{
    @Value("${DbUrl}")
    String test;

    @Override
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(
            @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        // TODO Auto-generated method stub
        return ClothesServiceApi.super.authenticateUser(authenticationRequestDTO);
    }

    @Override
    public ResponseEntity<Void> deleteGarment(BigDecimal id) {
        // TODO Auto-generated method stub
        return ClothesServiceApi.super.deleteGarment(id);
    }

    @Override
    public ResponseEntity<AuthenticationResponseDTO> getClothes(@Valid String sellerName, @Valid String sellerId,
            @Valid String garmentType, @Valid Float minPrice, @Valid Float maxPrice, @Valid String size) {
        // TODO Auto-generated method stub
        return ClothesServiceApi.super.getClothes(sellerName, sellerId, garmentType, minPrice, maxPrice, size);
    }

    @Override
    public ResponseEntity<Void> publishNewGarment(@Valid GarmentRequestDTO garmentRequestDTO) {
        // TODO Auto-generated method stub
        return ClothesServiceApi.super.publishNewGarment(garmentRequestDTO);
    }

    @Override
    public ResponseEntity<Void> updateGarment(BigDecimal id, @Valid GarmentRequestDTO garmentRequestDTO) {
        // TODO Auto-generated method stub
        return ClothesServiceApi.super.updateGarment(id, garmentRequestDTO);
    }
    
}
