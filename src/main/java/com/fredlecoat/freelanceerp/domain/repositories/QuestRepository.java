package com.fredlecoat.freelanceerp.domain.repositories;

import com.fredlecoat.freelanceerp.domain.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuestRepository extends JpaRepository<Quest, Long> {
    List<Quest> findAllByCustomer_Id(Long id);
}
