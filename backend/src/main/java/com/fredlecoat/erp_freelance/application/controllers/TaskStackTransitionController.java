package com.fredlecoat.erp_freelance.application.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackTransitionEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTransitionTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTransitionWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.TaskStackTransitionMapper;
import com.fredlecoat.erp_freelance.domain.services.TaskStackTransitionService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("task_stack_transitions")
@AllArgsConstructor
public class TaskStackTransitionController {

    private final TaskStackTransitionService taskStackTransitionService;
    private final TaskStackTransitionMapper taskStackTransitionMapper;

    @GetMapping
    public ResponseEntity<List<TaskStackTransitionTotalResponse>> getAll() {
        return ResponseEntity.ok(
            taskStackTransitionService.getAll()
                .stream()
                .map(taskStackTransitionMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskStackTransitionTotalResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
            taskStackTransitionMapper.toDto(taskStackTransitionService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskStackTransitionTotalResponse> create(
        @RequestBody TaskStackTransitionWithoutIdRequest request
    ) {
        TaskStackTransitionEntity entity = taskStackTransitionService.create(
            taskStackTransitionMapper.toEntity(request)
        );
        return ResponseEntity.status(HttpStatus.CREATED).body(
            taskStackTransitionMapper.toDto(entity)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskStackTransitionTotalResponse> update(
        @PathVariable Long id,
        @RequestBody TaskStackTransitionWithoutIdRequest dto
    ) {
        TaskStackTransitionEntity updated = taskStackTransitionService.update(
            taskStackTransitionMapper.toEntity(dto),
            id
        );
        return ResponseEntity.ok(taskStackTransitionMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskStackTransitionService.delete(id);
        return ResponseEntity.ok().build();
    }
}
