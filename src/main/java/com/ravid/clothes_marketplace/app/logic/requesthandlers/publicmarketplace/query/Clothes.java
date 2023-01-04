package com.ravid.clothes_marketplace.app.logic.requesthandlers.publicmarketplace.query;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ravid.clothes_marketplace.app.db.model.Garment;
import com.ravid.clothes_marketplace.app.db.model.Publisher;
import com.ravid.clothes_marketplace.app.db.repo.GarmentRepository;
import com.ravid.clothes_marketplace.app.logic.requesthandlers.RequestHandler;
import com.ravid.clothes_marketplace.server.model.ClothesResponseDTO;
import com.ravid.clothes_marketplace.server.model.GarmentResponseDTO;
import com.ravid.clothes_marketplace.server.model.GarmentSize;
import com.ravid.clothes_marketplace.server.model.GarmentType;
import com.ravid.clothes_marketplace.server.model.PublisherDTO;

@Component("clothes")
@Scope(value = WebApplicationContext.SCOPE_REQUEST,proxyMode = ScopedProxyMode.DEFAULT)
@Lazy
public class Clothes extends RequestHandler {

    @Autowired
    GarmentRepository repo;

    private Optional<String> publisherNamePattern;
    private Optional<String> publisherId;
    private Optional<String> type;
    private Optional<String> size;
    private Optional<Float>  minPrice ;
    private Optional<Float>  maxPrice;

    @Override
    @SuppressWarnings({"unchecked"})
    public <T> ResponseEntity<T> handleRequest(){
        List<Garment> queryResult = repo.findGarmentsBySearchParams(publisherNamePattern,
                                                                    publisherId,
                                                                    type,
                                                                    size,
                                                                    minPrice,
                                                                    maxPrice);


        ClothesResponseDTO res = new ClothesResponseDTO();
        
        // Construct the result DTO from the query result - 
        // The results are sorted, so when creating new Publisher items it is possible go sequentially through the results
        Optional<Publisher> currentPublisher = Optional.empty();
        PublisherDTO publisherListItem = null;
        for (Garment garment : queryResult) {
            if (!garment.getPublisher().equals(currentPublisher.orElseGet(() -> new Publisher()))) {
                currentPublisher = Optional.of(garment.getPublisher());
                publisherListItem = new PublisherDTO().address(garment.getPublisher().getAddress())
                                                      .fullName(garment.getPublisher().getFullName())
                                                      .id(garment.getPublisher().getIdNumber());
                res.addPublisherListItem(publisherListItem);
            }

            publisherListItem.addGarmentsItem(new GarmentResponseDTO()
                .garmentId(garment.getId())
                .garmentDescription(garment.getDescription()) 
                .garmentSize(GarmentSize.fromValue(garment.getSize()))
                .garmentType(GarmentType.fromValue(garment.getGarmentType()))
                .price(garment.getPrice())
            );
        }
        
        return (ResponseEntity<T>) ResponseEntity.ok(res);
    }

    // Abstract class initilization
    public Clothes(String publisherNamePattern,
                    String publisherId,
                    String type,
                    Float minPrice,
                    Float maxPrice,
                    String size) {
        super(null);
        this.publisherNamePattern = Optional.ofNullable(publisherNamePattern);
        this.publisherId = Optional.ofNullable(publisherId);
        this.type = Optional.ofNullable(type);
        this.minPrice  = Optional.ofNullable(minPrice);
        this.maxPrice = Optional.ofNullable(maxPrice);
        this.size = Optional.ofNullable(size);
    }
}
