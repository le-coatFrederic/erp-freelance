package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.HistoryEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.HistoryTotalResponse;

@Component
public class HistoryMapper {

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    public HistoryTotalResponse toDto(HistoryEntity entity) {
        return new HistoryTotalResponse(
            entity.getId(),
            this.messageTemplateMapper.toDto(entity.getMessageTemplate()),
            entity.getChangeType(),
            entity.getCreatedOn()
        );
    }
}
