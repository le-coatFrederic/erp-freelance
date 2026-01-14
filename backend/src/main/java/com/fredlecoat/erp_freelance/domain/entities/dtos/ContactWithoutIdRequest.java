package com.fredlecoat.erp_freelance.domain.entities.dtos;

public record ContactWithoutIdRequest(
    String firstname,
    String lastname,
    String job,
    String email,
    String phone,
    String linkedin,
    Long company_id
) {
    
}
