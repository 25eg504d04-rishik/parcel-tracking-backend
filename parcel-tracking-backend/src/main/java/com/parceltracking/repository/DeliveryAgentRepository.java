package com.parceltracking.repository;

import com.parceltracking.entity.DeliveryAgent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DeliveryAgentRepository
        extends JpaRepository<DeliveryAgent, Long> {

    Optional<DeliveryAgent> findByEmail(String email);

    boolean existsByEmail(String email);
}