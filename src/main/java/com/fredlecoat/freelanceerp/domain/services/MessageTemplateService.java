package com.fredlecoat.freelanceerp.domain.services;

import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;

import java.util.List;

public interface MessageTemplateService {
    public List<MessageTemplate> findAll();
    public List<MessageTemplate> findBySubject(String subject);
    public MessageTemplate create(MessageTemplate messageTemplate);
    public MessageTemplate update(MessageTemplate messageTemplate);
    public MessageTemplate findById(Long id);
    public void delete(Long id);
}
