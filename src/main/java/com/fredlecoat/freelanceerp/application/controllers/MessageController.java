package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.MessageCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.MessageShowAllDTO;
import com.fredlecoat.freelanceerp.application.mappers.MessageMapper;
import com.fredlecoat.freelanceerp.domain.entities.Message;
import com.fredlecoat.freelanceerp.domain.services.MessageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/messages")
public class MessageController {
    private final MessageService messageService;
    private final MessageMapper messageMapper;

    public MessageController(MessageService messageService, MessageMapper messageMapper) {
        this.messageService = messageService;
        this.messageMapper = messageMapper;
    }

    @GetMapping
    public ResponseEntity<List<MessageShowAllDTO>> index() {
        List<Message> messages = this.messageService.findAll();
        List<MessageShowAllDTO> messageDTOs = messages.stream()
                .map(messageMapper::toDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(messageDTOs);
    }

    @PostMapping
    public ResponseEntity<MessageShowAllDTO> create(@RequestBody MessageCreateDTO messageDTO) {
        Message messageEntity = this.messageMapper.toEntity(messageDTO);
        Message createdMessage = this.messageService.create(messageEntity);
        MessageShowAllDTO responseDTO = this.messageMapper.toDTO(createdMessage);

        return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MessageShowAllDTO> show(@PathVariable Long id) {
        Message message = this.messageService.findById(id);
        if (message == null) {
            return ResponseEntity.notFound().build();
        }

        MessageShowAllDTO responseDTO = this.messageMapper.toDTO(message);
        return ResponseEntity.ok(responseDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MessageShowAllDTO> update(@PathVariable Long id, @RequestBody MessageCreateDTO messageUpdateDTO) {
        Message existingMessage = this.messageService.findById(id);
        if (existingMessage == null) {
            return ResponseEntity.notFound().build();
        }

        Message messageEntity = this.messageMapper.toEntity(messageUpdateDTO);
        messageEntity.setId(id); // S'assurer que l'ID est conservé
        Message updatedMessage = this.messageService.update(messageEntity);
        MessageShowAllDTO responseDTO = this.messageMapper.toDTO(updatedMessage);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Message existingMessage = this.messageService.findById(id);
        if (existingMessage == null) {
            return ResponseEntity.notFound().build();
        }

        this.messageService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
