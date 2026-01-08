package com.medgenius.dto.response;

public class DashboardResponse {

    public long totalMedicines;
    public long lowStockCount;
    public long expiringSoonCount;
    public long todaySalesCount;

    public DashboardResponse(
            long totalMedicines,
            long lowStockCount,
            long expiringSoonCount,
            long todaySalesCount) {

        this.totalMedicines = totalMedicines;
        this.lowStockCount = lowStockCount;
        this.expiringSoonCount = expiringSoonCount;
        this.todaySalesCount = todaySalesCount;
    }
}

