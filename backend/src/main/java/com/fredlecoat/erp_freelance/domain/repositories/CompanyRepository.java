package com.fredlecoat.erp_freelance.domain.repositories;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity, Long> {
}
