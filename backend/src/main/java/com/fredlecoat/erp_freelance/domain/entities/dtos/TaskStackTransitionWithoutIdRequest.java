package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.TransitionType;

public record TaskStackTransitionWithoutIdRequest(
    Long source_stack_id,
    Long destination_stack_id,
    TransitionType transition_type,
    Long auto_transition_threshold_hours
) {}
