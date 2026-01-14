package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;
import com.fredlecoat.erp_freelance.domain.repositories.TaskRepository;
import com.fredlecoat.erp_freelance.domain.services.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Override
    public List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public TaskEntity create(TaskEntity entity) {
        return this.taskRepository.save(entity);
    }

    @Override
    public TaskEntity update(TaskEntity entity, Long id) {
        TaskEntity found = this.taskRepository.findById(id).orElseThrow();
        entity.updateWithOldData(found);
        return this.taskRepository.save(entity);
    }

    @Override
    public TaskEntity getById(Long id) {
        return this.taskRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.taskRepository.deleteById(id);
    }
}
