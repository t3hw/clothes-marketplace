package com.ravid.clothes_marketplace.app.db.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ravid.clothes_marketplace.app.db.model.User;

@Repository
public interface UserRepository extends JpaRepository<User,String> {
    
}
