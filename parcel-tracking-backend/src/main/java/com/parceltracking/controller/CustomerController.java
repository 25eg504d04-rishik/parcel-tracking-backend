package com.parceltracking.controller;

import com.parceltracking.entity.Customer;
import com.parceltracking.service.CustomerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customers")
@CrossOrigin(origins = "*")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    // Customer Registration
    @PostMapping("/register")
    public ResponseEntity<Customer> registerCustomer(
            @RequestBody Customer customer) {

        Customer savedCustomer = customerService.registerCustomer(customer);

        return ResponseEntity.ok(savedCustomer);
    }

    // Customer Login
    @PostMapping("/login")
    public ResponseEntity<Customer> loginCustomer(
            @RequestBody Customer customer) {

        Customer loggedInCustomer = customerService.loginCustomer(
                customer.getEmail(),
                customer.getPassword());

        return ResponseEntity.ok(loggedInCustomer);
    }
}