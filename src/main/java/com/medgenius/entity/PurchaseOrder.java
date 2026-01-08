package com.medgenius.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity
public class PurchaseOrder {
    @Id
    @GeneratedValue
    private Long id;

    private Long pharmacyId;
    private Long supplierId;

    private LocalDateTime orderDate;
    private String status;
}
