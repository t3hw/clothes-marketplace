package com.ravid.clothes_marketplace.app.db.repo.fragmentrepositories;

import java.util.List;
import java.util.Optional;

import com.ravid.clothes_marketplace.app.db.model.Garment;

public interface DynamicGarmentRepository {
    public List<Garment> findGarmentsBySearchParams(Optional<String> publisherNamePattern
                                                   ,Optional<String> publisherId
                                                   ,Optional<String> type
                                                   ,Optional<String> size
                                                   ,Optional<Float> minPrice 
                                                   ,Optional<Float> maxPrice);
}