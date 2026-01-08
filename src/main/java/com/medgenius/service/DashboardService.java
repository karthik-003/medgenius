package com.medgenius.service;

import com.medgenius.context.PharmacyContext;
import com.medgenius.dto.response.DashboardResponse;
import com.medgenius.repository.MedicineRepository;
import com.medgenius.repository.SalesRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Service
public class DashboardService {

    private static final int LOW_STOCK_THRESHOLD = 60;

    private final MedicineRepository medicineRepository;
    private final SalesRepository saleRepository;
    private final PharmacyContext pharmacyContext;

    public DashboardService(
            MedicineRepository medicineRepository,
            SalesRepository saleRepository,PharmacyContext pharmacyContext) {
        this.medicineRepository = medicineRepository;
        this.saleRepository = saleRepository;
        this.pharmacyContext = pharmacyContext;
    }

    public DashboardResponse getDashboard() {

        LocalDate today = LocalDate.now();
        LocalDateTime startOfDay = today.atStartOfDay();
        LocalDateTime endOfDay = today.atTime(23, 59, 59);

        long totalMedicines =
                medicineRepository.countByPharmacyId(pharmacyContext.getCurrentPharmacyId());

        long lowStockCount =
                medicineRepository.countByPharmacyIdAndQuantityLessThan(
                        pharmacyContext.getCurrentPharmacyId(), LOW_STOCK_THRESHOLD);

        long expiringSoonCount =
                medicineRepository.countByPharmacyIdAndExpiryDateBetween(
                        pharmacyContext.getCurrentPharmacyId(), today, today.plusDays(30));

        long todaySalesCount =
                saleRepository.countByPharmacyIdAndSaleDateBetween(
                        pharmacyContext.getCurrentPharmacyId(), startOfDay, endOfDay);

        return new DashboardResponse(
                totalMedicines,
                lowStockCount,
                expiringSoonCount,
                todaySalesCount
        );
    }
}

