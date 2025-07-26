package com.fredlecoat.freelanceerp.domain.repositories;

import com.fredlecoat.freelanceerp.domain.entities.Quest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestRepository extends JpaRepository<Quest, Long> {
}
