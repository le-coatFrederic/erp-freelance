package com.fredlecoat.erp_freelance.domain.entities.dtos;

import java.time.LocalDateTime;

import com.fredlecoat.erp_freelance.domain.entities.values.HistoryChangeType;

public record HistoryTotalResponse(
    Long id,
    MessageTemplateTotalResponse message_template,
    HistoryChangeType change_type,
    LocalDateTime date_time
) {

}
