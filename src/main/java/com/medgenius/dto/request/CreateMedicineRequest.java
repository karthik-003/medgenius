package com.medgenius.dto.request;

import java.time.LocalDate;

public class CreateMedicineRequest {
    public String name;
    public String batch;
    public Integer quantity;
    public LocalDate expiryDate;
    public Long pharmacyId;
}
