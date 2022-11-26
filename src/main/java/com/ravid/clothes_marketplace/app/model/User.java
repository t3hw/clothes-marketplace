package com.ravid.clothes_marketplace.app.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

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
