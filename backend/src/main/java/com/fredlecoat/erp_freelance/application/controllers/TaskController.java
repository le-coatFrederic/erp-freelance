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

import com.fredlecoat.erp_freelance.domain.entities.TaskEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskMoveRequest;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.TaskMapper;
import com.fredlecoat.erp_freelance.domain.services.TaskService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("tasks")
@AllArgsConstructor
public class TaskController {

    private final TaskService taskService;
    private final TaskMapper taskMapper;

    @GetMapping
    public ResponseEntity<List<TaskTotalResponse>> getAll() {
        return ResponseEntity.ok(
            taskService.getAll()
                .stream()
                .map(taskMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskTotalResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
            taskMapper.toDto(taskService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskTotalResponse> create(
        @RequestBody TaskWithoutIdRequest request
    ) {
        TaskEntity entity = taskService.create(taskMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskTotalResponse> update(
        @PathVariable Long id,
        @RequestBody TaskWithoutIdRequest dto
    ) {
        TaskEntity updated = taskService.update(taskMapper.toEntity(dto), id);
        return ResponseEntity.ok(taskMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskService.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/move")
    public ResponseEntity<TaskTotalResponse> moveTask(
        @PathVariable Long id,
        @RequestBody TaskMoveRequest request
    ) {
        TaskEntity movedTask = taskService.moveTask(
            id,
            request.destination_stack_id(),
            request.transition_id()
        );
        return ResponseEntity.ok(taskMapper.toDto(movedTask));
    }
}