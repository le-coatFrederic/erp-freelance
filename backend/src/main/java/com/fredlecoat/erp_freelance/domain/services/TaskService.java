package com.fredlecoat.erp_freelance.domain.services;

import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;

public interface TaskService extends EntityCrudService<TaskEntity> {
    TaskEntity moveTask(Long taskId, Long destinationStackId, Long transitionId);
}
