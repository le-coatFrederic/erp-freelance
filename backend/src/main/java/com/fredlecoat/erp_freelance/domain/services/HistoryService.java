package com.fredlecoat.erp_freelance.domain.services;

import java.util.List;

import com.fredlecoat.erp_freelance.domain.entities.HistoryEntity;
import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;

public interface HistoryService {
    public List<HistoryEntity> getAll();
    public HistoryEntity create(TaskEntity now, TaskEntity before);
    public HistoryEntity create(TaskEntity newTask);
    public HistoryEntity getById(Long id);
}
