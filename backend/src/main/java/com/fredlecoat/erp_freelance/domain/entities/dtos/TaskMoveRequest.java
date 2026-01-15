package com.fredlecoat.erp_freelance.domain.entities.dtos;

public record TaskMoveRequest(
    Long destination_stack_id,
    Long transition_id
) {}
