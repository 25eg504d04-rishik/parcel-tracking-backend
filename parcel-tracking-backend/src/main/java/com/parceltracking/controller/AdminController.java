package com.parceltracking.controller;

import com.parceltracking.entity.Customer;
import com.parceltracking.entity.DeliveryAgent;
import com.parceltracking.entity.Parcel;

import com.parceltracking.service.AdminService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    // Admin dashboard statistics
    @GetMapping("/dashboard")
    public ResponseEntity<Map<String, Long>> getDashboard() {
        return ResponseEntity.ok(
                adminService.getDashboardStatistics());
    }

    // View all customers
    @GetMapping("/customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        return ResponseEntity.ok(
                adminService.getAllCustomers());
    }

    // View all delivery agents
    @GetMapping("/agents")
    public ResponseEntity<List<DeliveryAgent>> getAllAgents() {
        return ResponseEntity.ok(
                adminService.getAllAgents());
    }

    // View all parcels
    @GetMapping("/parcels")
    public ResponseEntity<List<Parcel>> getAllParcels() {
        return ResponseEntity.ok(
                adminService.getAllParcels());
    }

    // Parcel status report
    @GetMapping("/reports/status")
    public ResponseEntity<Map<String, Long>> getParcelStatusReport() {
        return ResponseEntity.ok(
                adminService.getParcelStatusReport());
    }
}