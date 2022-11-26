package com.ravid.clothes_marketplace.app.model;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher,String> {
    List<Publisher> findByFullNameContainingIgnoreCase(String name);
}
