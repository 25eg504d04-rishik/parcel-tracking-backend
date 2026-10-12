
package com.parceltracking.service;

import com.parceltracking.entity.Customer;
import com.parceltracking.entity.DeliveryAgent;
import com.parceltracking.entity.Parcel;

import com.parceltracking.repository.CustomerRepository;
import com.parceltracking.repository.DeliveryAgentRepository;
import com.parceltracking.repository.ParcelRepository;

import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AdminService {

    private final CustomerRepository customerRepository;
    private final DeliveryAgentRepository agentRepository;
    private final ParcelRepository parcelRepository;

    public AdminService(
            CustomerRepository customerRepository,
            DeliveryAgentRepository agentRepository,
            ParcelRepository parcelRepository) {

        this.customerRepository = customerRepository;
        this.agentRepository = agentRepository;
        this.parcelRepository = parcelRepository;
    }

    // Dashboard counts
    public Map<String, Long> getDashboardStatistics() {

        Map<String, Long> statistics = new LinkedHashMap<>();

        statistics.put("totalCustomers", customerRepository.count());
        statistics.put("totalAgents", agentRepository.count());
        statistics.put("totalParcels", parcelRepository.count());

        statistics.put(
                "bookedParcels",
                parcelRepository.countByStatus("BOOKED"));

        statistics.put(
                "inTransitParcels",
                parcelRepository.countByStatus("IN_TRANSIT"));

        statistics.put(
                "deliveredParcels",
                parcelRepository.countByStatus("DELIVERED"));

        statistics.put(
                "cancelledParcels",
                parcelRepository.countByStatus("CANCELLED"));

        return statistics;
    }

    // Parcel count grouped by status
    public Map<String, Long> getParcelStatusReport() {

        return parcelRepository.findAll()
                .stream()
                .collect(Collectors.groupingBy(
                        parcel -> parcel.getStatus() == null
                                ? "UNKNOWN"
                                : parcel.getStatus(),
                        LinkedHashMap::new,
                        Collectors.counting()));
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public List<DeliveryAgent> getAllAgents() {
        return agentRepository.findAll();
    }

    public List<Parcel> getAllParcels() {
        return parcelRepository.findAll();
    }
}