package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.AttachmentEntity;
import com.fredlecoat.erp_freelance.domain.repositories.AttachmentRepository;
import com.fredlecoat.erp_freelance.domain.services.AttachmentService;

@Service
public class AttachmentServiceImpl implements AttachmentService {

    @Autowired
    private AttachmentRepository attachmentRepository;

    @Override
    public List<AttachmentEntity> getAll() {
        return this.attachmentRepository.findAll();
    }

    @Override
    public AttachmentEntity create(AttachmentEntity entity) {
        return this.attachmentRepository.save(entity);
    }

    @Override
    public AttachmentEntity update(AttachmentEntity entity, Long id) {
        Optional<AttachmentEntity> foundEntity = Optional.of(this.attachmentRepository.findById(id).orElseThrow());
        entity.setId(foundEntity.get().getId());
        return this.attachmentRepository.save(entity);
    }

    @Override
    public AttachmentEntity getById(Long id) {
        return Optional.of(this.attachmentRepository.findById(id)).orElseThrow().get();
    }

    @Override
    public void delete(Long id) {
        this.attachmentRepository.deleteById(id);
    }

}
