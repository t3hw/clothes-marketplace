package com.ravid.clothes_marketplace.app.db.model;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "garments")
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Getter
@Setter

@NamedQuery(name = "Garment.getGarmentsByPublisherPartialName", query = """
    select g
    from Garment g
    where lower(g.publisher.fullName) like lower(concat('%', ?1,'%'))
"""
)
@NamedQuery(name = "Garment.getGarmentsByPublisherId", query = """
    select g
    from Garment g
    where g.publisher.idNumber = ?1
"""
)
@NamedQuery(name = "Garment.getGarmentsWithMinPrice", query = """
    select g
    from Garment g
    where g.price > ?1
"""
)
@NamedQuery(name = "Garment.getGarmentsWithMaxPrice", query = """
    select g
    from Garment g
    where g.price < ?1
"""
)
@NamedQuery(
        name  = "Garment.deleteGarmByGarmId",
        query = "delete from Garment where id = ?1"
)
@NamedQuery(
        name  = "Garment.updateGarmByGarmId",
        query = """
    update Garment 
    set garmentType = ?2,
        size = ?3,
        description = ?4,
        price = ?5
    where id = ?1
"""
)
public class Garment {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id", nullable = false, updatable = false, insertable= false)
    BigDecimal id;

    @Column(name = "garment_type",nullable = false, insertable = true, updatable = true, length = 10)
    @NonNull
    String garmentType;

    @ManyToOne
    @JoinColumn(name="publisher_id", nullable=false, insertable = true, updatable = false)
    Publisher publisher;
    
    @Column(name = "size",nullable = false, insertable = true, updatable = true, length = 2)
    @NonNull
    String size;

    @Column(name = "description",nullable = false, insertable = true, updatable = true, length = 100)
    String description;

    @Column(name = "price",nullable = false, insertable = true, updatable = true)
    @NonNull
    Float price;

}