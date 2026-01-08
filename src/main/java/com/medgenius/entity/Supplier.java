package com.medgenius.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Supplier {
    @Id
    @GeneratedValue
    private Long id;

    private Long pharmacyId;
    private String name;
    private String phone;
}
