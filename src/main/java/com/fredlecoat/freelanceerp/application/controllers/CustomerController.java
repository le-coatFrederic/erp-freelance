package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.CustomerCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.CustomerShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.CustomerMapper;
import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.services.CustomerService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerController {
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;

    public CustomerController(CustomerService customerService, CustomerMapper customerMapper) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
    }

    @GetMapping
    public ResponseEntity<List<CustomerShowAllDTO>> index() {
        List<Customer> customers = this.customerService.findAll();
        List<CustomerShowAllDTO> customerDTOs = customers.stream()
                .map(customerMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(customerDTOs);
    }

    @PostMapping
    public ResponseEntity<CustomerShowAllDTO> create(@RequestBody CustomerCreateDTO customerCreateDTO) {
        Customer customerEntity = this.customerMapper.toEntity(customerCreateDTO);
        Customer createdCustomer = this.customerService.create(customerEntity);
        CustomerShowAllDTO responseDTO = this.customerMapper.toDTO(createdCustomer);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerShowAllDTO> show(@PathVariable Long id) {
        Customer customer = this.customerService.findById(id);
        if (customer == null) {
            return ResponseEntity.notFound().build();
        }

        CustomerShowAllDTO responseDTO = this.customerMapper.toDTO(customer);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerShowAllDTO> update(
            @PathVariable Long id,
            @RequestBody CustomerCreateDTO customerUpdateDTO
    ) {
        Customer existingCustomer = this.customerService.findById(id);
        if (existingCustomer == null) {
            return ResponseEntity.notFound().build();
        }

        Customer customerEntity = this.customerMapper.toEntity(customerUpdateDTO);
        customerEntity.setId(id);
        Customer updatedCustomer = this.customerService.update(customerEntity);
        CustomerShowAllDTO responseDTO = this.customerMapper.toDTO(updatedCustomer);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Customer existingCustomer = this.customerService.findById(id);
        if (existingCustomer == null) {
            return ResponseEntity.notFound().build();
        }

        this.customerService.delete(existingCustomer);
        return ResponseEntity.noContent().build();
    }
}
