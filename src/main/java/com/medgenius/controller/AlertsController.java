package com.medgenius.controller;

import com.medgenius.entity.Alert;
import com.medgenius.service.AlertsService;
import jakarta.websocket.server.PathParam;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "http://localhost:5173")
@RestController
@RequestMapping("/api/alerts")
public class AlertsController {

    AlertsService alertsService;

    public AlertsController(AlertsService alertsService){
        this.alertsService = alertsService;
    }

    @GetMapping
    public List<Alert> getAllAlerts(){
        return alertsService.getAllAlerts();
    }

    @PutMapping("/{alertId}")
    public ResponseEntity<String> resolveAlert(@PathVariable Long alertId){
        alertsService.resolveAlert(alertId);
        return ResponseEntity.ok("Alert Resolved Successfully");
    }

    @GetMapping("/count")
    public ResponseEntity<Long> getActiveAlertsCount(@RequestParam boolean resolved){
        return ResponseEntity.ok(alertsService.countActiveAlerts(resolved));
    }


}
