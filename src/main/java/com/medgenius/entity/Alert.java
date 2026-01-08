package com.medgenius.entity;

import com.medgenius.util.AlertType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Table(name = "alert")
@Data
public class Alert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pharmacyId;

    private AlertType type; // LOW_STOCK, EXPIRY
    private String message;

    private Boolean resolved = false;

    private LocalDateTime createdAt = LocalDateTime.now();
}