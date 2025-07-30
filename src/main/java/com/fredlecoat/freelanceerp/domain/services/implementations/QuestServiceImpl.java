package com.fredlecoat.freelanceerp.domain.services.implementations;

import com.fredlecoat.freelanceerp.domain.entities.Quest;
import com.fredlecoat.freelanceerp.domain.repositories.QuestRepository;
import com.fredlecoat.freelanceerp.domain.services.QuestService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestServiceImpl implements QuestService {
    private QuestRepository questRepository;

    QuestServiceImpl(QuestRepository questRepository) {
        this.questRepository = questRepository;
    }

    @Override
    public List<Quest> findAll() {
        return this.questRepository.findAll();
    }

    @Override
    public Quest findById(Long id) {
        return this.questRepository.findById(id).orElseThrow();
    }

    @Override
    public Quest save(Quest quest) {
        return this.questRepository.save(quest);
    }

    @Override
    public Quest update(Quest quest) {
        return this.questRepository.save(quest);
    }

    @Override
    public void delete(Quest quest) {
        this.questRepository.delete(quest);
    }

    @Override
    public List<Quest> findByCustomer(Long customerId) {
        return this.questRepository.findAllByCustomer_Id(customerId);
    }
}
