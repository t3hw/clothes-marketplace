package com.ravid.clothes_marketplace.app.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import com.ravid.clothes_marketplace.app.db.model.Garment;
import com.ravid.clothes_marketplace.app.db.model.Publisher;
import com.ravid.clothes_marketplace.app.db.repo.fragmentrepositories.DynamicGarmentRepository;

@Repository
public interface GarmentRepository extends DynamicGarmentRepository, JpaRepository<Garment, Integer>{
    public List<Garment> findAllByPublisher(Publisher publisher);
    public List<Garment> findAllBySize(String size);
    // public Integer deleteById(Integer id);
    
    /* Named Queries */

    public List<Garment> getGarmentsByPublisherPartialName(String string);
    public List<Garment> getGarmentsByPublisherId(String id);
    public List<Garment> getGarmentsWithMinPrice(Float minPrice);
    public List<Garment> getGarmentsWithMaxPrice(Float maxPrice);
    @Modifying
    public Integer deleteGarmByGarmId(Integer id);
    @Modifying
    public void updateGarmByGarmId(Integer id, String type, String size, String description ,Float price);
}
