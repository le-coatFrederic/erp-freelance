package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.TaskCategory;

public record TaskTotalResponse(
    Long id,
    String name,
    String description,
    TaskCategory category,
    MessageTemplateTotalResponse message_template,
    ContactTotalResponse contact,
    TaskStackTotalResponse task_stack
) {}