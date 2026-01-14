package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.AttachmentEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.AttachmentTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.AttachmentWithoutIdRequest;

@Component
public class AttachmentMapper {

    public AttachmentEntity toEntity(AttachmentWithoutIdRequest dto) {
        return new AttachmentEntity(
            dto.name(),
            dto.description(),
            dto.type()
        );
    }

    public AttachmentTotalResponse toDto(AttachmentEntity entity) {
        return new AttachmentTotalResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getType()
        );
    }
}
