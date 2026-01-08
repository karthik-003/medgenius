package com.medgenius.config;

import com.medgenius.entity.Medicine;
import com.medgenius.entity.User;
import com.medgenius.repository.MedicineRepository;
import com.medgenius.repository.UserRepository;
import com.medgenius.service.ExpiryAlertService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner loadMedicines(MedicineRepository medicineRepository,
                                    UserRepository userRepository){
        return args -> {
            Long pharmacyId = 1L;
            if(medicineRepository.count() == 0){
                List<Medicine> medicines = List.of(
                        createMedicine(pharmacyId, "Paracetamol 500mg", "PCM-A1", 120, LocalDate.now().plusMonths(18)),
                        createMedicine(pharmacyId, "Azithromycin 250mg", "AZI-B2", 60, LocalDate.now().plusMonths(12)),
                        createMedicine(pharmacyId, "Amoxicillin 500mg", "AMX-C3", 40, LocalDate.now().plusMonths(10)),
                        createMedicine(pharmacyId, "Cetirizine 10mg", "CET-D4", 90, LocalDate.now().plusMonths(24)),
                        createMedicine(pharmacyId, "Pantoprazole 40mg", "PAN-E5", 70, LocalDate.now().plusMonths(8)),
                        createMedicine(pharmacyId, "Vitamin D3", "VIT-F6", 30, LocalDate.now().plusDays(20)),
                        createMedicine(pharmacyId, "ORS Sachet", "ORS-G7", 200, LocalDate.now().plusMonths(5))
                );

                medicineRepository.saveAll(medicines);
            }

            if(userRepository.count() == 0){
                List<User> mockUsers = List.of(
                        createUser(pharmacyId,"karthik","karthik","owner",true),
                        createUser(pharmacyId,"ramya","ramya","owner",true)
                );
                userRepository.saveAll(mockUsers);
            }

        };
    }

    @Bean
    CommandLineRunner runExpiryAlerts(ExpiryAlertService expiryAlertService) {
        return args -> expiryAlertService.generateExpiryAlerts();
    }



    private Medicine createMedicine(
            Long pharmacyId,
            String name,
            String batch,
            Integer quantity,
            LocalDate expiryDate) {

        Medicine m = new Medicine();
        m.setPharmacyId(pharmacyId);
        m.setName(name);
        m.setBatch(batch);
        m.setQuantity(quantity);
        m.setExpiryDate(expiryDate);
        m.setActive(true);
        return m;
    }

    private User createUser(Long pharmacy,String userName, String password,
                            String role,Boolean active){
        User u = new User();
        u.setRole(role);
        u.setUsername(userName);
        u.setPassword(password);
        u.setPharmacyId(pharmacy);
        u.setActive(active);
        return u;
    }
}
