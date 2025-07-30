package com.fredlecoat.freelanceerp.domain.services.implementations;

import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.repositories.ContactRepository;
import com.fredlecoat.freelanceerp.domain.services.ContactService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {
    ContactRepository contactRepository;

    ContactServiceImpl(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    @Override
    public Contact create(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null");
        }

        return this.contactRepository.save(contact);
    }

    @Override
    public Contact update(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact must not be null");
        }

        if (!this.contactRepository.existsById(contact.getId())) {
            throw new IllegalArgumentException("Contact with id " + contact.getId() + " does not exist");
        }

        return this.contactRepository.save(contact);
    }

    @Override
    public void delete(Contact contact) {
        this.contactRepository.delete(contact);
    }

    @Override
    public List<Contact> findAll() {
        return this.contactRepository.findAll();
    }

    @Override
    public Contact findById(Long id) {
        return this.contactRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Contact> findByCustomer(Long customerId) {
        return this.contactRepository.findAllByCustomer_Id(customerId);
    }

    @Override
    public List<Contact> findByEmail(String email) {
        return this.contactRepository.findAllByEmailLike(email);
    }
}
