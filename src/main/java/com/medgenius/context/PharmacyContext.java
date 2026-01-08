package com.medgenius.context;

import org.springframework.stereotype.Component;

@Component
public class PharmacyContext {

    private static final ThreadLocal<Long> PHARMACY_ID =
            new ThreadLocal<>();

    public void setPharmacyId(Long pharmacyId) {
        PHARMACY_ID.set(pharmacyId);
    }

    public Long getCurrentPharmacyId() {
        return PHARMACY_ID.get();
    }

    public void clear() {
        PHARMACY_ID.remove();
    }
}

