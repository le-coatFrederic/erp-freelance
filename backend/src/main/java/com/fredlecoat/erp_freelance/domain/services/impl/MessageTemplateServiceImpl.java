package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.MessageTemplateEntity;
import com.fredlecoat.erp_freelance.domain.repositories.MessageTemplateRepository;
import com.fredlecoat.erp_freelance.domain.services.MessageTemplateService;

@Service
public class MessageTemplateServiceImpl implements MessageTemplateService {

    @Autowired
    private MessageTemplateRepository messageTemplateRepository;

    @Override
    public List<MessageTemplateEntity> getAll() {
        return this.messageTemplateRepository.findAll();
    }

    @Override
    public MessageTemplateEntity create(MessageTemplateEntity entity) {
        return this.messageTemplateRepository.save(entity);
    }

    @Override
    public MessageTemplateEntity update(MessageTemplateEntity entity, Long id) {
        Optional<MessageTemplateEntity> foundEntity = Optional.of(this.messageTemplateRepository.findById(id).orElseThrow());
        entity.updateWithOldData(foundEntity.get());
        return this.messageTemplateRepository.save(entity);
    }

    @Override
    public MessageTemplateEntity getById(Long id) {
        return Optional.of(this.messageTemplateRepository.findById(id)).orElseThrow().get();
    }

    @Override
    public void delete(Long id) {
        this.messageTemplateRepository.deleteById(id);
    }

}
