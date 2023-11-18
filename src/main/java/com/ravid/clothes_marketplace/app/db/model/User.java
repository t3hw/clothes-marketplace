package com.ravid.clothes_marketplace.app.db.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

import lombok.Getter;

@Entity
@Table(name = "users")
@Getter
public class User {
    @Id
    @Column(name="id_number",nullable = false, insertable = false, updatable = false)
    String id;

    @Column(name = "password",nullable = false, insertable = false, updatable = false, length = 60)
    String password;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    Publisher publisher;
}
