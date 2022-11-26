package com.ravid.clothes_marketplace.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GarmentRepository extends JpaRepository<Garment, Integer>{
    public List<Garment> findAllByPublisher(Publisher publisher);
    public List<Garment> findAllBySize(String size);
    
    /* Named Queries */

    public List<Garment> getGarmentsByPublisherPartialName(String string);
    public List<Garment> getGarmentsByPublisherId(String id);
    public List<Garment> getGarmentsWithMinPrice(Float minPrice);
    public List<Garment> getGarmentsWithMaxPrice(Float maxPrice);
}
