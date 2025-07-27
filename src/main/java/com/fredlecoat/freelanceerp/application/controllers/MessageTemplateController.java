package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.MessageTemplateMapper;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.services.MessageTemplateService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages-templates")
public class MessageTemplateController {
    private final MessageTemplateService messageTemplateService;
    private final MessageTemplateMapper messageTemplateMapper;

    MessageTemplateController(
            MessageTemplateService messageTemplateService,
            MessageTemplateMapper messageTemplateMapper
    ) {
        this.messageTemplateService = messageTemplateService;
        this.messageTemplateMapper = messageTemplateMapper;
    }

    @GetMapping
    public ResponseEntity<List<MessageTemplateShowAllDTO>> index() {
        List<MessageTemplate> messageTemplates = this.messageTemplateService.findAll();
        List<MessageTemplateShowAllDTO> messageTemplateDTOs = messageTemplates.stream()
                .map(messageTemplateMapper::toAllDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageTemplateDTOs);
    }

    @PostMapping
    public ResponseEntity<MessageTemplateShowAllDTO> create(@RequestBody MessageTemplateCreateDTO messageTemplateCreateDTO) {
        MessageTemplate messageTemplateEntity = this.messageTemplateMapper.toEntity(messageTemplateCreateDTO);
        MessageTemplate createdMessageTemplate = this.messageTemplateService.create(messageTemplateEntity);
        MessageTemplateShowAllDTO responseDTO = this.messageTemplateMapper.toAllDTO(createdMessageTemplate);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageTemplateShowAllDTO> show(@PathVariable Long id) {
        MessageTemplate messageTemplate = this.messageTemplateService.findById(id);
        if (messageTemplate == null) {
            return ResponseEntity.notFound().build();
        }

        MessageTemplateShowAllDTO responseDTO = this.messageTemplateMapper.toAllDTO(messageTemplate);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageTemplateShowAllDTO> update(@PathVariable Long id, @RequestBody MessageTemplateCreateDTO messageTemplateUpdateDTO) {
        MessageTemplate existingMessageTemplate = this.messageTemplateService.findById(id);
        if (existingMessageTemplate == null) {
            return ResponseEntity.notFound().build();
        }

        MessageTemplate messageTemplateEntity = this.messageTemplateMapper.toEntity(messageTemplateUpdateDTO);
        messageTemplateEntity.setId(id); // S'assurer que l'ID est conservé
        MessageTemplate updatedMessageTemplate = this.messageTemplateService.update(messageTemplateEntity);
        MessageTemplateShowAllDTO responseDTO = this.messageTemplateMapper.toAllDTO(updatedMessageTemplate);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        MessageTemplate existingMessageTemplate = this.messageTemplateService.findById(id);
        if (existingMessageTemplate == null) {
            return ResponseEntity.notFound().build();
        }

        this.messageTemplateService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
