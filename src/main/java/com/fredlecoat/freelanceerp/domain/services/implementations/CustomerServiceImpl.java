package com.fredlecoat.freelanceerp.domain.services.implementations;

import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.repositories.CustomerRepository;
import com.fredlecoat.freelanceerp.domain.services.CustomerService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    CustomerRepository customerRepository;

    CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public Customer create(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Customer with id " + customer.getId() + " already exists");
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer update(Customer customer) {
        if (customer == null) {
            throw new IllegalArgumentException("Customer cannot be null");
        }

        if (!customerRepository.existsById(customer.getId())) {
            throw new IllegalArgumentException("Customer with id " + customer.getId() + " does not exist");
        }

        return customerRepository.save(customer);
    }

    @Override
    public Customer findById(Long id) {
        return this.customerRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Customer> findByName(String name) {
        List<Customer> customers = new ArrayList<>();
        this.customerRepository.findAll().forEach(customer -> {
            if (customer.getName().equals(name)) {
                customers.add(customer);
            }
        });

        return customers;
    }

    @Override
    public List<Customer> findAll() {
        return this.customerRepository.findAll();
    }

    @Override
    public void delete(Customer customer) {
        this.customerRepository.delete(customer);
    }
}
