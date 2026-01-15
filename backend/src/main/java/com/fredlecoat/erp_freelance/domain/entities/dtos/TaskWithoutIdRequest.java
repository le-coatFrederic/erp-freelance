package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.TaskCategory;

public record TaskWithoutIdRequest(
    Long message_template_id,
    Long contact_id,
    Long task_stack_id,
    String name,
    String description,
    TaskCategory category
) {}
