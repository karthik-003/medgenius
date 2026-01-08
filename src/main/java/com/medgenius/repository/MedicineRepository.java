package com.medgenius.repository;

import com.medgenius.entity.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine,Long> {

    List<Medicine> findByPharmacyId(Long pharmacyId);
    Optional<Medicine> findByIdAndPharmacyId(Long id, Long pharmacyId);
    List<Medicine> findByPharmacyIdAndExpiryDateBetween(
            Long pharmacyId,
            LocalDate start,
            LocalDate end
    );
    long countByPharmacyId(Long pharmacyId);

    long countByPharmacyIdAndQuantityLessThan(
            Long pharmacyId,
            Integer quantity
    );

    long countByPharmacyIdAndExpiryDateBetween(
            Long pharmacyId,
            LocalDate start,
            LocalDate end
    );

}
