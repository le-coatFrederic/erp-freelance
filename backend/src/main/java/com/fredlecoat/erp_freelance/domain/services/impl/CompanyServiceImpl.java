package com.fredlecoat.erp_freelance.domain.services.impl;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;
import com.fredlecoat.erp_freelance.domain.repositories.CompanyRepository;
import com.fredlecoat.erp_freelance.domain.services.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyServiceImpl implements CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(
            CompanyRepository companyRepository
    ) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<CompanyEntity> getAll() {
        return this.companyRepository.findAll();
    }

    @Override
    public CompanyEntity create(CompanyEntity entity) {
        return this.companyRepository.save(entity);
    }

    @Override
    public CompanyEntity update(CompanyEntity entity, Long id) {
        Optional<CompanyEntity> foundEntity = Optional.of(this.companyRepository.findById(id).orElseThrow());
        entity.setId(foundEntity.get().getId());
        return this.companyRepository.save(entity);
    }

    @Override
    public CompanyEntity getById(Long id) {
        return Optional.of(this.companyRepository.getById(id)).orElseThrow();
    }

    @Override
    public void delete(Long id) {
        this.companyRepository.deleteById(id);
    }
}
