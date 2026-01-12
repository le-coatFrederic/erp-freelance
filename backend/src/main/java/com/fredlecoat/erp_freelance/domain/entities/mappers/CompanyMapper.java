package com.fredlecoat.erp_freelance.domain.entities.mappers;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.CompanyTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.CompanyWithoutIdRequest;
import org.springframework.stereotype.Component;

@Component
public class CompanyMapper {
    public CompanyEntity toEntity(CompanyWithoutIdRequest dto) {
        return new CompanyEntity(
                dto.name(),
                dto.siret(),
                dto.category(),
                dto.size()
        );
    }

    public CompanyTotalResponse toDto(CompanyEntity entity) {
        return new CompanyTotalResponse(
                entity.getId(),
                entity.getName(),
                entity.getSiret(),
                entity.getCategory(),
                entity.getSize()
        );
    }
}
