package com.fredlecoat.freelanceerp.application.dtos;

import com.fredlecoat.freelanceerp.domain.values.QuestStatus;

import java.time.LocalDate;

public record QuestShowAllDTO(
        Long id,
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        QuestStatus status,
        CustomerShowAllDTO customerId
) {
}
