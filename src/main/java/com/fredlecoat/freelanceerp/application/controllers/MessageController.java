package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.MessageCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.MessageMapper;
import com.fredlecoat.freelanceerp.domain.entities.Message;
import com.fredlecoat.freelanceerp.domain.services.MessageService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final MessageMapper messageMapper;

    public MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @GetMapping("/")
    public List<MessageShowAllDTO> index() {
        List<MessageShowAllDTO> messages = new ArrayList<>();
        this.messageService.findAll().forEach(message -> messages.add(messageMapper.toDTO(message)));
        return messages;
    }

    @PostMapping
    public MessageShowAllDTO create(@RequestBody MessageCreateDTO messageDTO) {
        Message message = this.messageService.create(messageMapper.toEntity(messageDTO));
        return messageMapper.toDTO(message);
    }
}
