package com.fredlecoat.erp_freelance.domain.services;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackTransitionEntity;

public interface TaskStackTransitionService extends EntityCrudService<TaskStackTransitionEntity> {
    boolean validateTransition(Long transitionId, Long sourceStackId, Long destinationStackId);
}
