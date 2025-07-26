package com.fredlecoat.freelanceerp.application.dtos;

import com.fredlecoat.freelanceerp.domain.values.MessageStatus;

import java.time.LocalDate;

public record MessageCreateDTO(
        MessageStatus status,
        LocalDate plannedDate,
        LocalDate sendDate,
        Long contactId,
        Long messageTemplateId
) {
}
