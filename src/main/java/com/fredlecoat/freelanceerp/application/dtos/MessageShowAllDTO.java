package com.fredlecoat.freelanceerp.application.dtos;

import com.fredlecoat.freelanceerp.domain.values.MessageStatus;

import java.time.LocalDate;

public record MessageShowAllDTO(
        Long id,
        MessageStatus status,
        LocalDate plannedDate,
        LocalDate sendDate,
        ContactShowAllDTO contact,
        MessageTemplateShowAllDTO template
) {
}
