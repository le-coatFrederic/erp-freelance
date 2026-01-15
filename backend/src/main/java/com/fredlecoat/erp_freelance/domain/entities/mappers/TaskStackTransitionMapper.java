package com.fredlecoat.erp_freelance.domain.entities.mappers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackTransitionEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTransitionTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTransitionWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.services.TaskStackService;

@Component
public class TaskStackTransitionMapper {

    @Autowired
    private TaskStackService taskStackService;

    @Autowired
    private TaskStackMapper taskStackMapper;

    public TaskStackTransitionEntity toEntity(TaskStackTransitionWithoutIdRequest dto) {
        return new TaskStackTransitionEntity(
            taskStackService.getById(dto.source_stack_id()),
            taskStackService.getById(dto.destination_stack_id()),
            dto.transition_type(),
            dto.auto_transition_threshold_hours()
        );
    }

    public TaskStackTransitionTotalResponse toDto(TaskStackTransitionEntity entity) {
        return new TaskStackTransitionTotalResponse(
            entity.getId(),
            taskStackMapper.toDto(entity.getSourceStack()),
            taskStackMapper.toDto(entity.getDestinationStack()),
            entity.getTransitionType(),
            entity.getAutoTransitionThresholdHours()
        );
    }
}
