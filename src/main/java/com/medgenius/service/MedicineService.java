package com.medgenius.service;

import com.medgenius.context.PharmacyContext;
import com.medgenius.dto.request.CreateMedicineRequest;
import com.medgenius.entity.Medicine;
import com.medgenius.repository.MedicineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicineService {


    MedicineRepository medicineRepository;
    PharmacyContext pharmacyContext;

    public MedicineService(MedicineRepository repository, PharmacyContext pharmacyContext){
        this.medicineRepository = repository;
        this.pharmacyContext = pharmacyContext;
    }

    public Medicine addMedicine(CreateMedicineRequest request) {
        Medicine medicine = new Medicine();
        medicine.setPharmacyId(pharmacyContext.getCurrentPharmacyId()); // TEMP
        medicine.setName(request.name);
        medicine.setBatch(request.batch);
        medicine.setQuantity(request.quantity);
        medicine.setExpiryDate(request.expiryDate);

        return medicineRepository.save(medicine);
    }

    public List<Medicine> getAllMedicines(){
        return medicineRepository.findByPharmacyId(1L);
    }
}
