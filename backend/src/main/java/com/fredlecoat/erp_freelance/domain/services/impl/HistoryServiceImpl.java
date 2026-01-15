package com.fredlecoat.erp_freelance.domain.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fredlecoat.erp_freelance.domain.entities.HistoryEntity;
import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;
import com.fredlecoat.erp_freelance.domain.entities.values.HistoryChangeType;
import com.fredlecoat.erp_freelance.domain.repositories.HistoryRepository;
import com.fredlecoat.erp_freelance.domain.services.HistoryService;

@Service
public class HistoryServiceImpl implements HistoryService {

    @Autowired
    private HistoryRepository historyRepository;

    @Override
    public List<HistoryEntity> getAll() {
        return this.historyRepository.findAll();
    }

    @Override
    public HistoryEntity create(TaskEntity now, TaskEntity before) {
        if (before == null) {
            return this.historyRepository.save(new HistoryEntity(now.getMessageTemplate(), HistoryChangeType.CREATE_TASK));
        }

        // Changement d'état de la tache 
        if (!now.getTaskStack().equals(before.getTaskStack())) {
            return this.historyRepository.save(new HistoryEntity(now.getMessageTemplate(), HistoryChangeType.STACK_CHANGE));
        }

        throw new RuntimeException("Pas de motif pour créer le HistoryEntity");
    }

    @Override
    public HistoryEntity create(TaskEntity newTask) {
        return this.create(newTask, null);
    }

    @Override
    public HistoryEntity getById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getById'");
    }

}
