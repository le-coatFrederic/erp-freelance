package com.fredlecoat.freelanceerp.domain.repositories;

import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageTemplateRepository extends JpaRepository<MessageTemplate, Long> {
    List<MessageTemplate> findAllBySubjectLike(String subject);
}
