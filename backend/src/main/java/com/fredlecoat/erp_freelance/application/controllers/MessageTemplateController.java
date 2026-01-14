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

import com.fredlecoat.erp_freelance.domain.entities.MessageTemplateEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.MessageTemplateTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.MessageTemplateWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.MessageTemplateMapper;
import com.fredlecoat.erp_freelance.domain.services.MessageTemplateService;

@RestController
@RequestMapping("message_templates")
public class MessageTemplateController {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    @GetMapping
    public ResponseEntity<List<MessageTemplateTotalResponse>> getAll() {
        return ResponseEntity.ok(this.messageTemplateService.getAll()
                .stream()
                .map(this.messageTemplateMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageTemplateTotalResponse> getById(@PathVariable Long id) {
        MessageTemplateTotalResponse dto = this.messageTemplateMapper.toDto(this.messageTemplateService.getById(id));
        return ResponseEntity.ok(dto);
    }

    @PostMapping
    public ResponseEntity<MessageTemplateTotalResponse> create(@RequestBody MessageTemplateWithoutIdRequest request) {
        MessageTemplateEntity entity = this.messageTemplateService.create(this.messageTemplateMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(this.messageTemplateMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageTemplateTotalResponse> update(
            @PathVariable Long id,
            @RequestBody MessageTemplateWithoutIdRequest dto) {

        MessageTemplateTotalResponse updated = this.messageTemplateMapper.toDto(
                this.messageTemplateService.update(this.messageTemplateMapper.toEntity(dto), id)
        );
        return ResponseEntity.status(HttpStatus.OK).body(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ) {
        this.messageTemplateService.delete(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

