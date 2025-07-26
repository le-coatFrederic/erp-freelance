package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageTemplateShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.MessageTemplateMapper;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.services.MessageTemplateService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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

    @GetMapping("/")
    public List<MessageTemplateShowAllDTO> getAll() {
        List<MessageTemplateShowAllDTO> messageTemplates = new ArrayList<>();
        this.messageTemplateService.findAll().forEach(messageTemplate -> {
           messageTemplates.add(this.messageTemplateMapper.toAllDTO(messageTemplate));
        });

        return messageTemplates;
    }

    @PostMapping
    public MessageTemplateShowAllDTO create(@RequestBody MessageTemplateCreateDTO messageTemplate) {
        return this.messageTemplateMapper.toAllDTO(this.messageTemplateService.create(this.messageTemplateMapper.toEntity(messageTemplate)));
    }
}
