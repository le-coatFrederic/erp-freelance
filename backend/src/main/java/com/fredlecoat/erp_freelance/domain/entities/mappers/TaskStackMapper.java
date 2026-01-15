package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackWithoutIdRequest;

@Component
public class TaskStackMapper {

    public TaskStackEntity toEntity(TaskStackWithoutIdRequest dto) {
        return new TaskStackEntity(
            dto.name(),
            dto.description()
        );
    }

    public TaskStackTotalResponse toDto(TaskStackEntity entity) {
        return new TaskStackTotalResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription()
        );
    }
}