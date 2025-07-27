package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.ContactCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.ContactShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.ContactMapper;
import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.services.ContactService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/contacts")
public class ContactController {
    private ContactService contactService;
    private ContactMapper contactMapper;

    public ContactController(ContactService contactService, ContactMapper contactMapper) {
        this.contactService = contactService;
        this.contactMapper = contactMapper;
    }

    @GetMapping
    public ResponseEntity<List<ContactShowAllDTO>> index() {
        List<Contact> contacts = this.contactService.findAll();
        List<ContactShowAllDTO> contactDTOs = contacts.stream()
                .map(contactMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(contactDTOs);
    }

    @PostMapping
    public ResponseEntity<ContactShowAllDTO> create(@RequestBody ContactCreateDTO contactCreateDTO) {
        Contact contactEntity = this.contactMapper.toEntity(contactCreateDTO);
        Contact createdContact = this.contactService.create(contactEntity);
        ContactShowAllDTO responseDTO = this.contactMapper.toDTO(createdContact);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactShowAllDTO> show(@PathVariable Long id) {
        Contact contact = this.contactService.findById(id);
        if (contact == null) {
            return ResponseEntity.notFound().build();
        }

        ContactShowAllDTO responseDTO = this.contactMapper.toDTO(contact);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactShowAllDTO> update(@PathVariable Long id, @RequestBody ContactCreateDTO contactUpdateDTO) {
        Contact existingContact = this.contactService.findById(id);
        if (existingContact == null) {
            return ResponseEntity.notFound().build();
        }

        Contact contactEntity = this.contactMapper.toEntity(contactUpdateDTO);
        contactEntity.setId(id); // S'assurer que l'ID est conservé
        Contact updatedContact = this.contactService.update(contactEntity);
        ContactShowAllDTO responseDTO = this.contactMapper.toDTO(updatedContact);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Contact existingContact = this.contactService.findById(id);
        if (existingContact == null) {
            return ResponseEntity.notFound().build();
        }

        this.contactService.delete(existingContact);
        return ResponseEntity.noContent().build();
    }
}
