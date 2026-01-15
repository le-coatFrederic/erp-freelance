package com.fredlecoat.erp_freelance.application.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fredlecoat.erp_freelance.domain.entities.dtos.HistoryTotalResponse;
import com.fredlecoat.erp_freelance.domain.entities.mappers.HistoryMapper;
import com.fredlecoat.erp_freelance.domain.services.HistoryService;

@RestController
@RequestMapping("histories")
public class HistoryController {

    @Autowired
    private HistoryService historyService;

    @Autowired
    private HistoryMapper historyMapper;

    @GetMapping
    public ResponseEntity<List<HistoryTotalResponse>> getAll() {
        return ResponseEntity.ok(this.historyService.getAll()
                .stream()
                .map(this.historyMapper::toDto)
                .toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<HistoryTotalResponse> getById(@PathVariable Long id) {
        HistoryTotalResponse dto = this.historyMapper.toDto(this.historyService.getById(id));
        return ResponseEntity.ok(dto);
    }

}
