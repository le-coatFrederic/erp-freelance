package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.CustomerCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.CustomerShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.CustomerMapper;
import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.services.CustomerService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping("/")
    public List<CustomerShowAllDTO> index() {
        List<CustomerShowAllDTO> customers = new ArrayList<>();
        this.customerService.findAll().forEach(customer -> {
            customers.add(customerMapper.toDTO(customer));
        });

        return customers;
    }

    @PostMapping
    public CustomerShowAllDTO create(@RequestBody CustomerCreateDTO customerCreateDTO) {
        Customer customer = this.customerService.create(this.customerMapper.toEntity(customerCreateDTO));
        return this.customerMapper.toDTO(customer);
    }
}
