package com.fredlecoat.erp_freelance.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fredlecoat.erp_freelance.domain.entities.AttachmentEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.AttachmentTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.AttachmentWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.AttachmentMapper;
import com.fredlecoat.erp_freelance.domain.services.AttachmentService;

@RestController
@RequestMapping("attachments")
public class AttachmentController {

    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentMapper attachmentMapper;

    @GetMapping
    public ResponseEntity<List<AttachmentTotalResponse>> getAllAttachments() {
        return ResponseEntity.ok(this.attachmentService.getAll()
                .stream()
                .map(this.attachmentMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AttachmentTotalResponse> getAttachment(@PathVariable Long id) {
        AttachmentTotalResponse dto = this.attachmentMapper.toDto(this.attachmentService.getById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<AttachmentTotalResponse> createAttachment(@RequestBody AttachmentWithoutIdRequest request) {
        AttachmentEntity entity = this.attachmentService.create(this.attachmentMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.attachmentMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AttachmentTotalResponse> update(
            @PathVariable Long id,
            @RequestBody AttachmentWithoutIdRequest dto) {

        AttachmentTotalResponse updated = this.attachmentMapper.toDto(
                this.attachmentService.update(this.attachmentMapper.toEntity(dto), id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        this.attachmentService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
