package com.ravid.clothes_marketplace.app.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravid.clothes_marketplace.app.db.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,String> {
    List<Publisher> findByFullNameContainingIgnoreCase(String name);
}
