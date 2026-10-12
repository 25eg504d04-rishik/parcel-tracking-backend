package com.parceltracking.service;

import com.parceltracking.entity.DeliveryAgent;
import com.parceltracking.repository.DeliveryAgentRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeliveryAgentService {

    private final DeliveryAgentRepository deliveryAgentRepository;

    public DeliveryAgentService(
            DeliveryAgentRepository deliveryAgentRepository) {
        this.deliveryAgentRepository = deliveryAgentRepository;
    }

    // Add a delivery agent
    public DeliveryAgent addAgent(DeliveryAgent agent) {

        if (deliveryAgentRepository.existsByEmail(agent.getEmail())) {
            throw new RuntimeException(
                    "Email is already registered");
        }

        agent.setStatus("AVAILABLE");

        return deliveryAgentRepository.save(agent);
    }

    // Get all delivery agents
    public List<DeliveryAgent> getAllAgents() {
        return deliveryAgentRepository.findAll();
    }

    // Get an agent by ID
    public DeliveryAgent getAgentById(Long id) {
        return deliveryAgentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Delivery agent not found"));
    }

    // Update agent details
    public DeliveryAgent updateAgent(
            Long id, DeliveryAgent updatedAgent) {

        DeliveryAgent agent = getAgentById(id);

        agent.setName(updatedAgent.getName());
        agent.setEmail(updatedAgent.getEmail());
        agent.setPhone(updatedAgent.getPhone());
        agent.setAddress(updatedAgent.getAddress());

        if (updatedAgent.getPassword() != null
                && !updatedAgent.getPassword().isBlank()) {
            agent.setPassword(updatedAgent.getPassword());
        }

        if (updatedAgent.getStatus() != null
                && !updatedAgent.getStatus().isBlank()) {
            agent.setStatus(updatedAgent.getStatus());
        }

        return deliveryAgentRepository.save(agent);
    }

    // Delete a delivery agent
    public void deleteAgent(Long id) {
        DeliveryAgent agent = getAgentById(id);
        deliveryAgentRepository.delete(agent);
    }
}