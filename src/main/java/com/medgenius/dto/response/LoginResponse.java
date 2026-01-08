package com.medgenius.dto.response;

public class LoginResponse {
    public Long userId;
    public Long pharmacyId;
    public String role;

    public LoginResponse(Long userId, Long pharmacyId, String role) {
        this.userId = userId;
        this.pharmacyId = pharmacyId;
        this.role = role;
    }
}
