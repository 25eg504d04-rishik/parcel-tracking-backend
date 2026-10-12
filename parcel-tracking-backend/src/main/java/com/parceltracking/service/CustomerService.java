package com.parceltracking.service;

import com.parceltracking.entity.Customer;
import com.parceltracking.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    // Customer Registration
    public Customer registerCustomer(Customer customer) {

        if (customerRepository.existsByEmail(customer.getEmail())) {
            throw new RuntimeException("Email already registered");
        }

        return customerRepository.save(customer);
    }

    // Customer Login
    public Customer loginCustomer(String email, String password) {

        Optional<Customer> customer = customerRepository.findByEmail(email);

        if (customer.isPresent() &&
                customer.get().getPassword().equals(password)) {

            return customer.get();
        }

        throw new RuntimeException("Invalid email or password");
    }
}