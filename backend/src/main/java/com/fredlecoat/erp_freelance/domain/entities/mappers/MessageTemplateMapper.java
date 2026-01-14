package com.fredlecoat.erp_freelance.domain.entities.mappers;

import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.MessageTemplateEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.MessageTemplateTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.MessageTemplateWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.services.AttachmentService;

@Component
public class MessageTemplateMapper {
    @Autowired
    private AttachmentService attachmentService;

    @Autowired
    private AttachmentMapper attachmentMapper;

    public MessageTemplateEntity toEntity(MessageTemplateWithoutIdRequest dto) {
        return new MessageTemplateEntity(
            dto.subject(),
            dto.description(),
            dto.type(),
            dto.content(),
            dto.attachments_id()
                .stream()
                .map(this.attachmentService::getById)
                .collect(Collectors.toSet())
        );
    }

    public MessageTemplateTotalResponse toDto(MessageTemplateEntity entity) {
        return new MessageTemplateTotalResponse(
            entity.getId(),
            entity.getSubject(),
            entity.getDescription(),
            entity.getType(),
            entity.getContent(),
            entity.getAttachments()
                .stream()
                .map(this.attachmentMapper::toDto)
                .toList()
        );
    }
}
