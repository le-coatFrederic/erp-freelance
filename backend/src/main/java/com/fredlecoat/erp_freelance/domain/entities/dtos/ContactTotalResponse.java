package com.fredlecoat.erp_freelance.domain.entities.dtos;

public record ContactTotalResponse(
    Long id,
    String firstName,
    String lastName,
    String job,
    String email,
    String linkedin,
    String phone,
    CompanyTotalResponse company
) {
    
}
