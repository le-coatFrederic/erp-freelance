package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;
import com.fredlecoat.erp_freelance.domain.entities.TaskStackEntity;
import com.fredlecoat.erp_freelance.domain.repositories.TaskRepository;
import com.fredlecoat.erp_freelance.domain.services.HistoryService;
import com.fredlecoat.erp_freelance.domain.services.TaskService;
import com.fredlecoat.erp_freelance.domain.services.TaskStackService;
import com.fredlecoat.erp_freelance.domain.services.TaskStackTransitionService;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private HistoryService historyService;

    @Autowired
    private TaskStackTransitionService taskStackTransitionService;

    @Autowired
    private TaskStackService taskStackService;

    @Override
    public List<TaskEntity> getAll() {
        return this.taskRepository.findAll();
    }

    @Override
    public TaskEntity create(TaskEntity entity) {
        TaskEntity newEntity = this.taskRepository.save(entity);
        this.historyService.create(newEntity);
        return newEntity;
    }

    @Override
    public TaskEntity update(TaskEntity entity, Long id) {
        TaskEntity found = this.taskRepository.findById(id).orElseThrow();

        entity.updateWithOldData(found);
        entity = this.taskRepository.save(entity);

        this.historyService.create(entity, found);
        return entity;
    }

    @Override
    public TaskEntity getById(Long id) {
        return this.taskRepository.findById(id).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.taskRepository.deleteById(id);
    }

    @Override
    public TaskEntity moveTask(Long taskId, Long destinationStackId, Long transitionId) {
        TaskEntity task = this.taskRepository.findById(taskId).orElseThrow(
            () -> new RuntimeException("Task not found")
        );

        Long currentStackId = task.getTaskStack().getId();

        boolean isValidTransition = this.taskStackTransitionService.validateTransition(
            transitionId,
            currentStackId,
            destinationStackId
        );

        if (!isValidTransition) {
            throw new RuntimeException(
                "Invalid transition: No transition exists from stack " +
                currentStackId + " to stack " + destinationStackId
            );
        }

        TaskEntity oldTask = new TaskEntity(
            task.getMessageTemplate(),
            task.getContact(),
            task.getTaskStack(),
            task.getName(),
            task.getDescription(),
            task.getCategory()
        );
        oldTask.updateWithOldData(task);

        TaskStackEntity destinationStack = this.taskStackService.getById(destinationStackId);
        task.setTaskStack(destinationStack);

        TaskEntity updatedTask = this.taskRepository.save(task);
        this.historyService.create(updatedTask, oldTask);

        return updatedTask;
    }
}
