package com.fredlecoat.erp_freelance.domain.entities.dtos;

public record TaskStackWithoutIdRequest(
    String name,
    String description
) {}