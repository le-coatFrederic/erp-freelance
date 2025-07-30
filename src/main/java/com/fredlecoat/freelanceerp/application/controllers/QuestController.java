package com.fredlecoat.freelanceerp.application.controllers;

import com.fredlecoat.freelanceerp.application.dtos.QuestCreateDTO;
import com.fredlecoat.freelanceerp.application.dtos.QuestShowAllDTO;
import com.fredlecoat.freelanceerp.application.dtos.QuestUpdateDTO;
import com.fredlecoat.freelanceerp.application.mappers.QuestMapper;
import com.fredlecoat.freelanceerp.domain.entities.Quest;
import com.fredlecoat.freelanceerp.domain.services.QuestService;
import com.fredlecoat.freelanceerp.domain.services.implementations.QuestServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/quests")
public class QuestController {
    private final QuestService questService;
    private final QuestMapper questMapper;

    public QuestController(QuestService questService, QuestMapper questMapper) {
        this.questService = questService;
        this.questMapper = questMapper;
    }

    @GetMapping
    public ResponseEntity<List<QuestShowAllDTO>> getAllQuests(@RequestParam(required = false) Long customerId) {
        List<Quest> quests = customerId == null ?
                this.questService.findAll() :
                this.questService.findByCustomer(customerId);
        List<QuestShowAllDTO> questShowAllDTOS = quests.stream()
                .map(questMapper::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(questShowAllDTOS);
    }

    @PostMapping
    public ResponseEntity<QuestShowAllDTO> createQuest(@RequestBody QuestCreateDTO questCreateDTO) {
        Quest quest = questMapper.toEntity(questCreateDTO);
        quest = questService.save(quest);
        QuestShowAllDTO questShowAllDTO = questMapper.toDTO(quest);
        return ResponseEntity.ok(questShowAllDTO);
    }
}
