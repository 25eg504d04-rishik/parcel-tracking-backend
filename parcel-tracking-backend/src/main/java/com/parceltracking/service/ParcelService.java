package com.parceltracking.service;

import com.parceltracking.entity.Parcel;
import com.parceltracking.entity.ParcelTrackingHistory;
import com.parceltracking.repository.ParcelRepository;
import com.parceltracking.repository.ParcelTrackingHistoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final ParcelTrackingHistoryRepository historyRepository;

    public ParcelService(
            ParcelRepository parcelRepository,
            ParcelTrackingHistoryRepository historyRepository) {
        this.parcelRepository = parcelRepository;
        this.historyRepository = historyRepository;
    }

    public Parcel bookParcel(Parcel parcel) {
        String trackingNumber;

        do {
            trackingNumber = "TRK" +
                    UUID.randomUUID().toString()
                            .replace("-", "")
                            .substring(0, 10)
                            .toUpperCase();
        } while (parcelRepository.existsByTrackingNumber(trackingNumber));

        parcel.setTrackingNumber(trackingNumber);
        parcel.setStatus("BOOKED");

        Parcel savedParcel = parcelRepository.save(parcel);

        saveHistory(savedParcel, "BOOKED", "Parcel booked successfully");

        return savedParcel;
    }

    public Parcel trackParcel(String trackingNumber) {
        return parcelRepository.findByTrackingNumber(trackingNumber)
                .orElseThrow(() -> new RuntimeException("Parcel not found"));
    }

    public Parcel updateParcelStatus(String trackingNumber, String status) {
        List<String> validStatuses = List.of(
                "BOOKED",
                "PICKED_UP",
                "IN_TRANSIT",
                "OUT_FOR_DELIVERY",
                "DELIVERED");

        if (status == null || !validStatuses.contains(status)) {
            throw new RuntimeException("Invalid parcel status");
        }

        Parcel parcel = trackParcel(trackingNumber);
        parcel.setStatus(status);

        Parcel updatedParcel = parcelRepository.save(parcel);

        saveHistory(updatedParcel, status, "Status updated to " + status);

        return updatedParcel;
    }

    public List<ParcelTrackingHistory> getTrackingHistory(String trackingNumber) {
        trackParcel(trackingNumber);

        return historyRepository
                .findByTrackingNumberOrderByUpdatedAtDesc(trackingNumber);
    }

    private void saveHistory(
            Parcel parcel, String status, String description) {

        ParcelTrackingHistory history = new ParcelTrackingHistory();
        history.setTrackingNumber(parcel.getTrackingNumber());
        history.setStatus(status);
        history.setDescription(description);

        historyRepository.save(history);
    }
}