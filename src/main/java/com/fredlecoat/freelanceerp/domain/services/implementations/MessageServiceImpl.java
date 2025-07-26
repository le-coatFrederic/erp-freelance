package com.fredlecoat.freelanceerp.domain.services.implementations;

import com.fredlecoat.freelanceerp.domain.entities.Contact;
import com.fredlecoat.freelanceerp.domain.entities.Message;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.repositories.MessageRepository;
import com.fredlecoat.freelanceerp.domain.services.MessageService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {
    MessageRepository messageRepository;

    MessageServiceImpl(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    @Override
    public Message create(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        if (this.messageRepository.existsById(message.getId())) {
            throw new IllegalArgumentException("Message with id " + message.getId() + " already exist");
        }

        return this.messageRepository.save(message);
    }

    @Override
    public Message update(Message message) {
        if (message == null) {
            throw new IllegalArgumentException("Message cannot be null");
        }

        if (!this.messageRepository.existsById(message.getId())) {
            throw new IllegalArgumentException("Message with id " + message.getId() + " does not exist");
        }

        return this.messageRepository.save(message);
    }

    @Override
    public void delete(Long id) {
        this.messageRepository.deleteById(id);
    }

    @Override
    public List<Message> findAll() {
        return this.messageRepository.findAll();
    }

    @Override
    public Message findById(Long id) {
        return this.messageRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Message> findByMessageTemplate(MessageTemplate messageTemplate) {
        List<Message> messages = new ArrayList<>();
        this.messageRepository.findAll().forEach(message -> {
            if (messageTemplate.getId().equals(message.getMessageTemplate().getId())) {
                messages.add(message);
            }
        });

        return messages;
    }

    @Override
    public List<Message> findByContact(Contact contact) {
        List<Message> messages = new ArrayList<>();
        this.messageRepository.findAll().forEach(message -> {
            if (contact.getId().equals(message.getContact().getId())) {
                messages.add(message);
            }
        });

        return messages;
    }
}
