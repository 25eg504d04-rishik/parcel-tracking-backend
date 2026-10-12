package com.parceltracking.controller;

import com.parceltracking.entity.DeliveryAgent;
import com.parceltracking.service.DeliveryAgentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/agents")
@CrossOrigin(origins = "*")
public class DeliveryAgentController {

    private final DeliveryAgentService deliveryAgentService;

    public DeliveryAgentController(
            DeliveryAgentService deliveryAgentService) {
        this.deliveryAgentService = deliveryAgentService;
    }

    // Add a delivery agent
    @PostMapping
    public ResponseEntity<DeliveryAgent> addAgent(
            @RequestBody DeliveryAgent agent) {
        return ResponseEntity.ok(
                deliveryAgentService.addAgent(agent));
    }

    // Get all delivery agents
    @GetMapping
    public ResponseEntity<List<DeliveryAgent>> getAllAgents() {
        return ResponseEntity.ok(
                deliveryAgentService.getAllAgents());
    }

    // Get an agent by ID
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryAgent> getAgentById(
            @PathVariable Long id) {
        return ResponseEntity.ok(
                deliveryAgentService.getAgentById(id));
    }

    // Update an agent
    @PutMapping("/{id}")
    public ResponseEntity<DeliveryAgent> updateAgent(
            @PathVariable Long id,
            @RequestBody DeliveryAgent agent) {
        return ResponseEntity.ok(
                deliveryAgentService.updateAgent(id, agent));
    }

    // Delete an agent
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAgent(
            @PathVariable Long id) {
        deliveryAgentService.deleteAgent(id);
        return ResponseEntity.ok(
                "Delivery agent deleted successfully");
    }
}