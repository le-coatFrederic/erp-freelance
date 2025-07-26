package com.fredlecoat.freelanceerp.application.dtos;

import com.fredlecoat.freelanceerp.domain.values.CustomerContactGap;
import com.fredlecoat.freelanceerp.domain.values.CustomerStatus;
import com.fredlecoat.freelanceerp.domain.values.CustomerType;

public record CustomerShowAllDTO(
        Long id,
        String name,
        String socialReason,
        String SIRET,
        String address,
        String mail,
        String phone,
        String website,
        CustomerType type,
        CustomerStatus status,
        CustomerContactGap gap
) {
}
