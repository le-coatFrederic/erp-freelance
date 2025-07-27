package com.fredlecoat.freelanceerp.application.dtos;

public record ContactUpdateDTO(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String linkedin,
        String jobTitle,
        Long customerId
) {
}
