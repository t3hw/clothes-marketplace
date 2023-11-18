package com.ravid.clothes_marketplace.app.db.model;

import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "publishers")
@Getter

public class Publisher {
    @Id
    @Column(name="id_number",nullable = false, insertable = false, updatable = false, length = 9)
    String idNumber;

    @Column(name = "full_name",nullable = false, insertable = false, updatable = false, length = 15)
    String fullName;

    @Column(name = "address",nullable = false, insertable = false, updatable = false, length = 30)
    String address;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinColumn(name="publisher_id", nullable=false, insertable = false, updatable = false)
    Set<Garment> garments;
    
    @OneToOne
    @MapsId
    @JoinColumn(name = "id_number")
    User user;
}
