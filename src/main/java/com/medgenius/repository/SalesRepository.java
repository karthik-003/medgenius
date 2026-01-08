package com.medgenius.repository;

import com.medgenius.entity.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SalesRepository extends JpaRepository<Sale,Long> {
    long countByPharmacyIdAndSaleDateBetween(
            Long pharmacyId,
            LocalDateTime start,
            LocalDateTime end
    );

}
