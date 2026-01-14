package com.fredlecoat.erp_freelance.domain.entities;

import com.fredlecoat.erp_freelance.domain.entities.values.MessageTemplateType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class MessageTemplateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private MessageTemplateType type;

    private String content;

    public MessageTemplateEntity(
        String subject,
        String description,
        MessageTemplateType type,
        String content
    ) {
        this.subject = subject;
        this.description = description;
        this.type = type;
        this.content = content;
    }

}
