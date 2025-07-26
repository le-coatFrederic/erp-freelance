package com.fredlecoat.freelanceerp.domain.services.implementations;

import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.repositories.MessageTemplateRepository;
import com.fredlecoat.freelanceerp.domain.services.MessageTemplateService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageTemplateServiceImpl implements MessageTemplateService {
    MessageTemplateRepository messageTemplateRepository;

    MessageTemplateServiceImpl(MessageTemplateRepository messageTemplateRepository) {
        this.messageTemplateRepository = messageTemplateRepository;
    }

    @Override
    public List<MessageTemplate> findAll() {
        return this.messageTemplateRepository.findAll();
    }

    @Override
    public List<MessageTemplate> findBySubject(String subject) {
        return this.messageTemplateRepository.findAllBySubjectLike(subject);
    }

    @Override
    public MessageTemplate create(MessageTemplate messageTemplate) {
        if (messageTemplate == null) {
            throw new IllegalArgumentException("Message template cannot be null");
        }

        if (this.messageTemplateRepository.existsById(messageTemplate.getId())) {
            throw new IllegalArgumentException("Message template with id " + messageTemplate.getId() + " already exists");
        }

        return this.messageTemplateRepository.save(messageTemplate);
    }

    @Override
    public MessageTemplate update(MessageTemplate messageTemplate) {
        if (messageTemplate == null) {
            throw new IllegalArgumentException("Message template cannot be null");
        }

        if (!this.messageTemplateRepository.existsById(messageTemplate.getId())) {
            throw new IllegalArgumentException("Message template with id " + messageTemplate.getId() + " does not exist");
        }

        return this.messageTemplateRepository.save(messageTemplate);
    }

    @Override
    public MessageTemplate findById(Long id) {
        return this.messageTemplateRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.messageTemplateRepository.deleteById(id);
    }
}
