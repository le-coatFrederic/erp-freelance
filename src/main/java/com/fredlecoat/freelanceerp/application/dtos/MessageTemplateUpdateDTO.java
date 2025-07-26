package com.fredlecoat.freelanceerp.application.dtos;

public record MessageTemplateUpdateDTO(
        Long id,
        String subject,
        String body
) {
}
