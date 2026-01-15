package com.fredlecoat.erp_freelance.domain.entities.mappers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackEntity;
import com.fredlecoat.erp_freelance.domain.entities.TaskStackTransitionEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTransitionTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.repositories.TaskStackTransitionRepository;

@Component
public class TaskStackMapper {

    @Autowired
    private TaskStackTransitionRepository taskStackTransitionRepository;

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
            entity.getDescription(),
            null
        );
    }

    public TaskStackTotalResponse toDtoWithTransitions(TaskStackEntity entity) {
        List<TaskStackTransitionEntity> transitions =
            taskStackTransitionRepository.findBySourceStackId(entity.getId());

        List<TaskStackTransitionTotalResponse> transitionDtos = transitions.stream()
            .map(t -> new TaskStackTransitionTotalResponse(
                t.getId(),
                toDto(t.getSourceStack()),
                toDto(t.getDestinationStack()),
                t.getTransitionType(),
                t.getAutoTransitionThresholdHours()
            ))
            .toList();

        return new TaskStackTotalResponse(
            entity.getId(),
            entity.getName(),
            entity.getDescription(),
            transitionDtos
        );
    }
}