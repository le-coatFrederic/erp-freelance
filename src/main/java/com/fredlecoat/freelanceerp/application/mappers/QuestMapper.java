package com.fredlecoat.freelanceerp.application.mappers;

import com.fredlecoat.freelanceerp.application.dtos.*;
import com.fredlecoat.freelanceerp.domain.entities.Customer;
import com.fredlecoat.freelanceerp.domain.entities.MessageTemplate;
import com.fredlecoat.freelanceerp.domain.entities.Quest;
import com.fredlecoat.freelanceerp.domain.services.CustomerService;
import com.fredlecoat.freelanceerp.domain.services.QuestService;

public class QuestMapper {
    private CustomerService customerService;
    private CustomerMapper customerMapper;
    private QuestService questService;

    public QuestMapper(CustomerService customerService, CustomerMapper customerMapper, QuestService questService) {
        this.customerService = customerService;
        this.customerMapper = customerMapper;
        this.questService = questService;
    }

    public Quest toEntity(QuestCreateDTO questDTO) {
        Customer customer = this.customerService.findById(questDTO.customerId());
        return new Quest(
                questDTO.title(),
                questDTO.description(),
                questDTO.startDate(),
                questDTO.endDate(),
                questDTO.status(),
                customer
        );
    }

    public Quest toEntity(QuestUpdateDTO questUpdateDTO) {
        Quest quest = this.questService.findById(questUpdateDTO.id());
        Customer customer = this.customerService.findById(questUpdateDTO.customerId());

        if (quest == null) {
            throw new IllegalArgumentException("Quest not found");
        }

        quest.setTitle(questUpdateDTO.title());
        quest.setDescription(questUpdateDTO.description());
        quest.setStartDate(questUpdateDTO.startDate());
        quest.setEndDate(questUpdateDTO.endDate());
        quest.setStatus(questUpdateDTO.status());
        quest.setCustomer(customer);

        return quest;
    }

    public QuestShowAllDTO toDTO(Quest quest) {
        CustomerShowAllDTO customer = this.customerMapper.toDTO(quest.getCustomer());

        return new QuestShowAllDTO(
                quest.getId(),
                quest.getTitle(),
                quest.getDescription(),
                quest.getStartDate(),
                quest.getEndDate(),
                quest.getStatus(),
                customer
        );
    }
}
