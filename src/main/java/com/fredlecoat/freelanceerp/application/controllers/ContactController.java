package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.ContactCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.ContactShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.ContactMapper;
import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.services.ContactService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private ContactService contactService;
    private ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @GetMapping("/")
    public List<ContactShowAllDTO> index() {
        List<ContactShowAllDTO> contacts = new ArrayList<>();
        this.contactService.findAll().forEach(contact -> contacts.add(contactMapper.toDTO(contact)));
        return contacts;
    }

    @PostMapping
    public ContactShowAllDTO create(@RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contact = this.contactService.create(contactMapper.toEntity(contactCreateDTO));
        return contactMapper.toDTO(contact);
    }
}
