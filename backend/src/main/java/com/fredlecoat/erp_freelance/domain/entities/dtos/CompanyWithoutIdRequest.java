package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.CompanyCategory;

public record CompanyWithoutIdRequest(
        String name,
        String siret,
        CompanyCategory category,
        int size
) {
}
