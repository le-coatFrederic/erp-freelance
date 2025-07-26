package com.fredlecoat.freelanceerp.domain.repositories;

import com.fredlecoat.freelanceerp.domain.entities.QuoteLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuoteLineRepository extends JpaRepository<QuoteLine, Long> {
}
