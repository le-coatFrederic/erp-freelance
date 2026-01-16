package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.ContactEntity;
import com.fredlecoat.erp_freelance.domain.repositories.ContactRepository;
import com.fredlecoat.erp_freelance.domain.services.ContactService;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    private ContactRepository contactRepository;

    @Override
    public List<ContactEntity> getAll() {
        return this.contactRepository.findAll();
    }

    @Override
    public ContactEntity create(ContactEntity entity) {
        return this.contactRepository.save(entity);
    }

    @Override
    public ContactEntity update(ContactEntity entity, Long id) {
        Optional<ContactEntity> foundEntity = Optional.of(this.contactRepository.findById(id).orElseThrow());
        entity.updateWithOldData(foundEntity.get());
        return this.contactRepository.save(entity);
    }

    @Override
    public ContactEntity getById(Long id) {
        return Optional.of(this.contactRepository.findById(id)).orElseThrow().get();
    }

    @Override
    public void delete(Long id) {
        this.contactRepository.deleteById(id);
    }

    @Override
    public List<ContactEntity> getAvailableForTask() {
        return this.contactRepository.findContactsWithoutActiveTasks();
    }

}
