package com.fredlecoat.freelanceerp.domain.repositories;

import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
}
