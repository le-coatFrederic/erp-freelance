package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.AttachmentType;

public record AttachmentTotalResponse(
    Long id,
    String name,
    String description,
    AttachmentType type
) {

}
