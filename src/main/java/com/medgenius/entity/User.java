package com.medgenius.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "app_user")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long pharmacyId;

    @Column(unique = true)
    private String username;
    private String password;

    private String role; // OWNER, STAFF

    private Boolean active = true;
}
