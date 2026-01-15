package com.fredlecoat.erp_freelance.domain.entities.dtos;

import com.fredlecoat.erp_freelance.domain.entities.values.TransitionType;

public record TaskStackTransitionTotalResponse(
    Long id,
    TaskStackTotalResponse source_stack,
    TaskStackTotalResponse destination_stack,
    TransitionType transition_type,
    Long auto_transition_threshold_hours
) {}
