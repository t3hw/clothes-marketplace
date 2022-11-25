package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.server.api.PublicMarketplaceApi;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;

public class PublicMarketplaceService implements PublicMarketplaceApi {
    @Value("${DbUrl}")
    String test;

    @Override
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(
            @Valid AuthenticationRequestDTO authenticationRequestDTO) {
        // TODO Auto-generated method stub
        return PublicMarketplaceApi.super.authenticateUser(authenticationRequestDTO);
    }

    @Override
    public ResponseEntity<AuthenticationResponseDTO> getClothes(@Valid String sellerName, @Valid String sellerId,
            @Valid String garmentType, @Valid Float minPrice, @Valid Float maxPrice, @Valid String size) {
        // TODO Auto-generated method stub
        return PublicMarketplaceApi.super.getClothes(sellerName, sellerId, garmentType, minPrice, maxPrice, size);
    }


}
