package com.fredlecoat.freelanceerp.domain.services;

import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.entities.Customer;

import java.util.List;

public interface ContactService {
    Contact create(Contact contact);
    Contact update(Contact contact);
    void delete(Contact contact);
    List<Contact> findAll();
    Contact findById(Long id);
    List<Contact> findByCustomer(Long customerId);
    List<Contact> findByEmail(String email);
}
