package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.MessageTemplateType;

public record MessageTemplateWithoutIdRequest(
    String subject,
    String description,
    MessageTemplateType type,
    String content
) {

}
