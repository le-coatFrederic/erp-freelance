package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;

import com.fredlecoat.erp_freelance.domain.entities.values.MessageTemplateType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

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

    @PrePersist
    private void createOn() {
        this.createdOn = Instant.now();
        this.updatedOn = Instant.now();
    }

    @PreUpdate
    private void updateOn() {
        this.updatedOn = Instant.now();
    }

    public void updateWithOldData(MessageTemplateEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }

}
