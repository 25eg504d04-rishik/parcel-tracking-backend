package com.parceltracking.repository;

import com.parceltracking.entity.ParcelTrackingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ParcelTrackingHistoryRepository
        extends JpaRepository<ParcelTrackingHistory, Long> {

    List<ParcelTrackingHistory> findByTrackingNumberOrderByUpdatedAtDesc(
            String trackingNumber);
}