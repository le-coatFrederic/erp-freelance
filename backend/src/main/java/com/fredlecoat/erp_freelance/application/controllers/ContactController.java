package com.fredlecoat.erp_freelance.application.controllers;

import com.fredlecoat.erp_freelance.domain.entities.ContactEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.ContactTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.ContactWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.ContactMapper;
import com.fredlecoat.erp_freelance.domain.services.ContactService;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("contacts")
@AllArgsConstructor
public class ContactController {

    private final ContactService contactService;

    private final ContactMapper contactMapper;

    @GetMapping
    public ResponseEntity<List<ContactTotalResponse>> getAllCompanies() {
        return ResponseEntity.ok(this.contactService.getAll()
                .stream()
                .map(this.contactMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/available-for-task")
    public ResponseEntity<List<ContactTotalResponse>> getAvailableForTask() {
        return ResponseEntity.ok(this.contactService.getAvailableForTask()
                .stream()
                .map(this.contactMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContactTotalResponse> getCompany(@PathVariable Long id) {
        ContactTotalResponse dto = this.contactMapper.toDto(this.contactService.getById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<ContactTotalResponse> createCompany(@RequestBody ContactWithoutIdRequest request) {
        ContactEntity entity = this.contactService.create(this.contactMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.contactMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ContactTotalResponse> update(
            @PathVariable Long id,
            @RequestBody ContactWithoutIdRequest dto) {

        ContactTotalResponse updated = this.contactMapper.toDto(
                this.contactService.update(this.contactMapper.toEntity(dto), id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        this.contactService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
