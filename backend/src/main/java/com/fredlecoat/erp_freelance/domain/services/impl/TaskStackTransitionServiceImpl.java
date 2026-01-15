package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackTransitionEntity;
import com.fredlecoat.erp_freelance.domain.repositories.TaskStackTransitionRepository;
import com.fredlecoat.erp_freelance.domain.services.TaskStackTransitionService;

@Service
public class TaskStackTransitionServiceImpl implements TaskStackTransitionService {

    @Autowired
    private TaskStackTransitionRepository taskStackTransitionRepository;

    @Override
    public List<TaskStackTransitionEntity> getAll() {
        return this.taskStackTransitionRepository.findAll();
    }

    @Override
    public TaskStackTransitionEntity create(TaskStackTransitionEntity entity) {
        return this.taskStackTransitionRepository.save(entity);
    }

    @Override
    public TaskStackTransitionEntity update(TaskStackTransitionEntity entity, Long id) {
        TaskStackTransitionEntity found = this.taskStackTransitionRepository.findById(id).orElseThrow();
        entity.updateWithOldData(found);
        return this.taskStackTransitionRepository.save(entity);
    }

    @Override
    public TaskStackTransitionEntity getById(Long id) {
        return this.taskStackTransitionRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.taskStackTransitionRepository.deleteById(id);
    }

    @Override
    public boolean validateTransition(Long transitionId, Long sourceStackId, Long destinationStackId) {
        return this.taskStackTransitionRepository.existsByIdAndSourceStackIdAndDestinationStackId(
            transitionId,
            sourceStackId,
            destinationStackId
        );
    }
}
