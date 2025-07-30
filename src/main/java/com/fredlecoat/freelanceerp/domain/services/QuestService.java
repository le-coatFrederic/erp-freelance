package com.fredlecoat.freelanceerp.domain.services;

import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.entities.Quest;

import java.util.List;

public interface QuestService {
    public List<Quest> findAll();
    public Quest findById(Long id);
    public Quest save(Quest quest);
    public Quest update(Quest quest);
    public void delete(Quest quest);
    public List<Quest> findByCustomer(Long customerId);
}
