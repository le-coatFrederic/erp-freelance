package com.fredlecoat.freelanceerp.domain.services;

import com.fredlecoat.freelanceerp.domain.entities.Customer;

import java.util.List;

public interface CustomerService {
    public Customer create(Customer customer);
    public Customer update(Customer customer);
    public Customer findById(Long id);
    public List<Customer> findByName(String name);
    public List<Customer> findAll();
    public void delete(Customer customer);
}
