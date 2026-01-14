package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.services.ContactService;
import com.fredlecoat.erp_freelance.domain.services.MessageTemplateService;

@Component
public class TaskMapper {

    @Autowired
    private MessageTemplateService messageTemplateService;

    @Autowired
    private ContactService contactService;

    @Autowired
    private ContactMapper contactMapper;

    @Autowired
    private MessageTemplateMapper messageTemplateMapper;

    public TaskEntity toEntity(TaskWithoutIdRequest dto) {
        return new TaskEntity(
            messageTemplateService.getById(dto.message_template_id()),
            contactService.getById(dto.contact_id()),
            dto.name(),
            dto.description(),
            dto.category()
        );
    }

    public TaskTotalResponse toDto(TaskEntity entity) {
        return new TaskTotalResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            entity.getCategory(),
            messageTemplateMapper.toDto(entity.getMessageTemplate()),
            contactMapper.toDto(entity.getContact())
        );
    }
}