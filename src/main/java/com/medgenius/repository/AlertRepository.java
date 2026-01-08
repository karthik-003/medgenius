package com.medgenius.repository;

import com.medgenius.entity.Alert;
import com.medgenius.util.AlertType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertRepository extends JpaRepository<Alert,Long> {
    boolean existsByPharmacyIdAndTypeAndMessage(
            Long pharmacyId,
            AlertType type,
            String message
    );

    List<Alert> findByPharmacyId(Long pharmacyId);
    long countByPharmacyIdAndResolved(Long pharmacy,Boolean resolved);

}
