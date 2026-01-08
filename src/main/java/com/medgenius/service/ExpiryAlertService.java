package com.medgenius.service;

import com.medgenius.context.PharmacyContext;
import com.medgenius.entity.Alert;
import com.medgenius.entity.Medicine;
import com.medgenius.repository.AlertRepository;
import com.medgenius.repository.MedicineRepository;
import com.medgenius.util.AlertType;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpiryAlertService {



    private final MedicineRepository medicineRepository;
    private final AlertRepository alertRepository;
    private final PharmacyContext pharmacyContext;

    public ExpiryAlertService(
            MedicineRepository medicineRepository,
            AlertRepository alertRepository,PharmacyContext pharmacyContext) {
        this.medicineRepository = medicineRepository;
        this.alertRepository = alertRepository;
        this.pharmacyContext =  pharmacyContext;
    }

    public void generateExpiryAlerts() {

        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(30);

        List<Medicine> expiringMedicines =
                medicineRepository.findByPharmacyIdAndExpiryDateBetween(
                        pharmacyContext.getCurrentPharmacyId(), today, threshold);

        for (Medicine medicine : expiringMedicines) {

            String message =
                    "Medicine " + medicine.getName() +
                            " (Batch " + medicine.getBatch() +
                            ") expires on " + medicine.getExpiryDate();

            boolean exists =
                    alertRepository.existsByPharmacyIdAndTypeAndMessage(
                            pharmacyContext.getCurrentPharmacyId(), AlertType.EXPIRY, message);

            if (!exists) {
                Alert alert = new Alert();
                alert.setPharmacyId(pharmacyContext.getCurrentPharmacyId());
                alert.setType(AlertType.EXPIRY);
                alert.setMessage(message);
                alertRepository.save(alert);
            }
        }
    }
}
