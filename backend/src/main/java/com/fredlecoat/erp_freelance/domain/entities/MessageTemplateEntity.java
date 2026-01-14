package com.fredlecoat.erp_freelance.domain.entities;

import com.fredlecoat.erp_freelance.domain.entities.values.MessageTemplateType;

import jakarta.persistence.Entity;
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

    private String subject;

    private String description;

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
