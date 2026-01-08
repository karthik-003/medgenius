package com.medgenius.service;

import com.medgenius.context.PharmacyContext;
import com.medgenius.entity.Alert;
import com.medgenius.repository.AlertRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlertsService {


    AlertRepository alertRepository;
    PharmacyContext pharmacyContext;

    public AlertsService(AlertRepository alertRepository, PharmacyContext pharmacyContext){
        this.alertRepository = alertRepository;
        this.pharmacyContext = pharmacyContext;
    }

    public List<Alert> getAllAlerts(){
        return alertRepository.findByPharmacyId(pharmacyContext.getCurrentPharmacyId());
    }

    public void resolveAlert(Long alertId){
        Alert alert = alertRepository.findById(alertId)
                .orElseThrow(() ->new RuntimeException("Invalid Alert"));
        alert.setResolved(true);
        alertRepository.save(alert);
    }

    public Long countActiveAlerts(boolean resolved){
        return alertRepository.countByPharmacyIdAndResolved(pharmacyContext.getCurrentPharmacyId(),
                resolved);
    }
}
