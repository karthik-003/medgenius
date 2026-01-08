package com.medgenius.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name="medicine")
@Data
public class Medicine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pharmacyId;

    private String name;
    private String batch;
    private Integer quantity;
    private LocalDate expiryDate;

    private Boolean active = true;
}
