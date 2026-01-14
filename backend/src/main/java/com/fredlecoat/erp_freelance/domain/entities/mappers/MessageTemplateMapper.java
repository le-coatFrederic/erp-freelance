package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.MessageTemplateEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.MessageTemplateTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.MessageTemplateWithoutIdRequest;

@Component
public class MessageTemplateMapper {

    public MessageTemplateEntity toEntity(MessageTemplateWithoutIdRequest dto) {
        return new MessageTemplateEntity(
            dto.subject(),
            dto.description(),
            dto.type(),
            dto.content()
        );
    }

    public MessageTemplateTotalResponse toDto(MessageTemplateEntity entity) {
        return new MessageTemplateTotalResponse(
            entity.getId(),
            entity.getSubject(),
            entity.getDescription(),
            entity.getType(),
            entity.getContent()
        );
    }
}
