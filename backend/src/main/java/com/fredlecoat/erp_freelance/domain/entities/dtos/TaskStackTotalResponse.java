package com.fredlecoat.erp_freelance.domain.entities.dtos;

import java.util.List;

public record TaskStackTotalResponse(
    Long id,
    String name,
    String description,
    List<TaskStackTransitionTotalResponse> outgoing_transitions
) {}