package com.medgenius.controller;

import com.medgenius.dto.request.SaleRecord;
import com.medgenius.service.SalesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SalesService saleService;

    public SaleController(SalesService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<String> sell(@RequestBody SaleRecord request) {
        saleService.recordSale(request);
        return ResponseEntity.ok("Sale recorded");
    }
}
