package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackEntity;
import com.fredlecoat.erp_freelance.domain.repositories.TaskStackRepository;
import com.fredlecoat.erp_freelance.domain.services.TaskStackService;

@Service
public class TaskStackServiceImpl implements TaskStackService {

    @Autowired
    private TaskStackRepository taskStackRepository;

    @Override
    public List<TaskStackEntity> getAll() {
        return this.taskStackRepository.findAll();
    }

    @Override
    public TaskStackEntity create(TaskStackEntity entity) {
        return this.taskStackRepository.save(entity);
    }

    @Override
    public TaskStackEntity update(TaskStackEntity entity, Long id) {
        TaskStackEntity found = taskStackRepository.findById(id).orElseThrow();
        entity.updateWithOldData(found);
        return taskStackRepository.save(entity);
    }

    @Override
    public TaskStackEntity getById(Long id) {
        return taskStackRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        taskStackRepository.deleteById(id);
    }
}
