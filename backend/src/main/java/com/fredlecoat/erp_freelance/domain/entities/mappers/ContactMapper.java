package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;
import com.fredlecoat.erp_freelance.domain.entities.ContactEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.ContactTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.ContactWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.services.CompanyService;

@Component
public class ContactMapper {
    @Autowired
    private CompanyService companyService;

    @Autowired
    private CompanyMapper companyMapper;

    public ContactEntity toEntity(ContactWithoutIdRequest dto) {
        CompanyEntity company = this.companyService.getById(dto.company_id());
        if (company == null) {
            throw new RuntimeException("Company not found");
        }

        return new ContactEntity(
            dto.firstName(),
            dto.lastName(),
            dto.job(),
            dto.email(),
            dto.phone(),
            dto.linkedin(),
            company
        );
    }

    public ContactTotalResponse toDto(ContactEntity entity) {
        return new ContactTotalResponse(
            entity.getId(),
            entity.getFirstname(),
            entity.getLastname(),
            entity.getJob(),
            entity.getEmail(),
            entity.getLinkedin(),
            entity.getPhone(),
            this.companyMapper.toDto(entity.getCompany())
        );
    }
}
