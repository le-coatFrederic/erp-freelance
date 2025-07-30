package com.fredlecoat.freelanceerp.application.dtos;

import com.fredlecoat.freelanceerp.domain.values.QuestStatus;

import java.time.LocalDate;

public record QuestCreateDTO(
        String title,
        String description,
        LocalDate startDate,
        LocalDate endDate,
        QuestStatus status,
        Long customerId
) {
}
