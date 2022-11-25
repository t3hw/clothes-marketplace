package com.ravid.clothes_marketplace.app.service;

import com.ravid.clothes_marketplace.server.api.PublicMarketplaceApiDelegate;
import com.ravid.clothes_marketplace.server.model.AuthenticationRequestDTO;
import com.ravid.clothes_marketplace.server.model.AuthenticationResponseDTO;
import com.ravid.clothes_marketplace.server.model.PublisherDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PublicMarketplaceService implements PublicMarketplaceApiDelegate {

    @Override
    public ResponseEntity<AuthenticationResponseDTO> authenticateUser(
            AuthenticationRequestDTO authenticationRequestDTO) {
        // TODO Auto-generated method stub
        System.out.println("hello");
        return ResponseEntity.ok(null);
    }

    @Override
    public ResponseEntity<PublisherDTO> getClothes(String sellerName, String sellerId, String garmentType,
            Float minPrice, Float maxPrice, String size) {
        // TODO Auto-generated method stub
        System.out.println("hello");
        return ResponseEntity.ok(new PublisherDTO());
    }

}
