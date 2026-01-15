package com.fredlecoat.erp_freelance.domain.entities.dtos;

import java.util.List;

import com.fredlecoat.erp_freelance.domain.entities.values.MessageTemplateType;

public record MessageTemplateTotalResponse(
    Long id,
    String subject,
    String description,
    MessageTemplateType type,
    String content,
    List<AttachmentTotalResponse> attachments
) {

}
