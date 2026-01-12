package com.fredlecoat.erp_freelance.domain.services;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;

import java.util.List;

public interface CompanyService {
    public List<CompanyEntity> getAll();
    public CompanyEntity create(CompanyEntity entity);
    public CompanyEntity update(CompanyEntity entity, Long id);
    public CompanyEntity getById(Long id);
    public void delete(Long id);
}
