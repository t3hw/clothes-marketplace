package com.ravid.clothes_marketplace.app.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

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
