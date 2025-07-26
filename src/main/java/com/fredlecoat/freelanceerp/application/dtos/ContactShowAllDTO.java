package com.fredlecoat.freelanceerp.application.dtos;

public record ContactShowAllDTO(
        Long id,
        String firstname,
        String lastname,
        String email,
        String phone,
        String linkedin,
        String jobTitle,
        CustomerShowAllDTO customer
) {
}
