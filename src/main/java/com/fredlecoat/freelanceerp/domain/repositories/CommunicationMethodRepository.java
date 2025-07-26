package com.fredlecoat.freelanceerp.domain.repositories;

import com.fredlecoat.freelanceerp.domain.entities.CommunicationMethod;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunicationMethodRepository extends JpaRepository<CommunicationMethod, Long> {
}
