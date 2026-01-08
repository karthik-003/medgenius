package com.medgenius.controller;

import com.medgenius.dto.request.CreateMedicineRequest;
import com.medgenius.entity.Medicine;
import com.medgenius.service.MedicineService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/medicines")
public class MedicineController {

    private final MedicineService service;

    public MedicineController(MedicineService service) {
        this.service = service;
    }

    @PostMapping
    public Medicine add(@RequestBody CreateMedicineRequest request) {
        return service.addMedicine(request);
    }

    @GetMapping
    public List<Medicine> list() {
        return service.getAllMedicines();
    }
}
