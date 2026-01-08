package com.medgenius.service;

import com.medgenius.context.PharmacyContext;
import com.medgenius.dto.request.SaleRecord;
import com.medgenius.entity.Alert;
import com.medgenius.entity.Medicine;
import com.medgenius.entity.Sale;
import com.medgenius.exception.BusinessException;
import com.medgenius.repository.AlertRepository;
import com.medgenius.repository.MedicineRepository;
import com.medgenius.repository.SalesRepository;
import com.medgenius.util.AlertType;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class SalesService {

    private final MedicineRepository medicineRepository;
    private final SalesRepository salesRepository;
    private final AlertRepository alertRepository;
    private final PharmacyContext pharmacyContext;

    public SalesService(MedicineRepository medicineRepository, SalesRepository salesRepository,
                        AlertRepository alertRepository, PharmacyContext pharmacyContext) {
        this.medicineRepository = medicineRepository;
        this.salesRepository = salesRepository;
        this.alertRepository = alertRepository;
        this.pharmacyContext = pharmacyContext;
    }

    @Transactional
    public void recordSale(SaleRecord record){
        Medicine medicine = medicineRepository.findByIdAndPharmacyId(record.medicineId,
                        pharmacyContext.getCurrentPharmacyId())
                .orElseThrow(() -> new IllegalArgumentException("Medicine not found"));

        if (medicine.getQuantity() < record.quantitySold) {
            throw new IllegalArgumentException("Insufficient stock");
        }

        // 1️⃣ Reduce stock
        int remaining = medicine.getQuantity() - record.quantitySold;
        medicine.setQuantity(remaining);
        medicineRepository.save(medicine);

        // 2️⃣ Save sale
        Sale sale = new Sale();
        sale.setPharmacyId(pharmacyContext.getCurrentPharmacyId());
        sale.setMedicineId(medicine.getId());
        sale.setQuantitySold(record.quantitySold);
        sale.setSaleDate(LocalDateTime.now());
        salesRepository.save(sale);

        //3. Create Alert is stock is less than 100
        if( remaining <= 100){
            Alert alert = new Alert();
            alert.setPharmacyId(pharmacyContext.getCurrentPharmacyId());
            alert.setType(AlertType.LOW_STOCK);
            alert.setMessage(
                    "Low stock for " + medicine.getName() + " (Remaining: " + remaining + ")"
            );
            alertRepository.save(alert);
        }
    }
}
