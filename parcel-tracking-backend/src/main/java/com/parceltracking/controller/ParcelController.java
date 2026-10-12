package com.parceltracking.controller;

import com.parceltracking.entity.Parcel;
import com.parceltracking.entity.ParcelTrackingHistory;
import com.parceltracking.service.ParcelService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/parcels")
@CrossOrigin(origins = "*")
public class ParcelController {

    private final ParcelService parcelService;

    public ParcelController(ParcelService parcelService) {
        this.parcelService = parcelService;
    }

    @PostMapping("/book")
    public ResponseEntity<Parcel> bookParcel(
            @RequestBody Parcel parcel) {

        return ResponseEntity.ok(
                parcelService.bookParcel(parcel));
    }

    @GetMapping("/track/{trackingNumber}")
    public ResponseEntity<Parcel> trackParcel(
            @PathVariable String trackingNumber) {

        return ResponseEntity.ok(
                parcelService.trackParcel(trackingNumber));
    }

    @PutMapping("/status/{trackingNumber}")
    public ResponseEntity<Parcel> updateStatus(
            @PathVariable String trackingNumber,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        return ResponseEntity.ok(
                parcelService.updateParcelStatus(
                        trackingNumber, status));
    }

    @GetMapping("/history/{trackingNumber}")
    public ResponseEntity<List<ParcelTrackingHistory>> getHistory(
            @PathVariable String trackingNumber) {

        return ResponseEntity.ok(
                parcelService.getTrackingHistory(trackingNumber));
    }
}