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

import com.fredlecoat.erp_freelance.domain.entities.TaskStackEntity;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.dtos.TaskStackWithoutIdRequest;
import com.fredlecoat.erp_freelance.domain.entities.mappers.TaskStackMapper;
import com.fredlecoat.erp_freelance.domain.services.TaskStackService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("task_stacks")
@AllArgsConstructor
public class TaskStackController {

    private final TaskStackService taskStackService;
    private final TaskStackMapper taskStackMapper;

    @GetMapping
    public ResponseEntity<List<TaskStackTotalResponse>> getAll() {
        return ResponseEntity.ok(
            taskStackService.getAll()
                .stream()
                .map(taskStackMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<TaskStackTotalResponse> get(@PathVariable Long id) {
        return ResponseEntity.ok(
            taskStackMapper.toDto(taskStackService.getById(id))
        );
    }

    @PostMapping
    public ResponseEntity<TaskStackTotalResponse> create(
        @RequestBody TaskStackWithoutIdRequest request
    ) {
        TaskStackEntity entity = taskStackService.create(taskStackMapper.toEntity(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(taskStackMapper.toDto(entity));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TaskStackTotalResponse> update(
        @PathVariable Long id,
        @RequestBody TaskStackWithoutIdRequest dto
    ) {
        TaskStackEntity updated = taskStackService.update(taskStackMapper.toEntity(dto), id);
        return ResponseEntity.ok(taskStackMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        taskStackService.delete(id);
        return ResponseEntity.ok().build();
    }
}