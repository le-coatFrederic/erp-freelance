package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import com.fredlecoat.erp_freelance.domain.entities.values.AttachmentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "attachments")
public class AttachmentEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 128)
    private String name;

    @Column(nullable = false, length = 1024)
    private String description;

    @Column(nullable = false)
    private AttachmentType type;

    @ManyToMany(mappedBy = "attachments")
    private Set<MessageTemplateEntity> messageTemplates = new HashSet<>();

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    public AttachmentEntity(
        String name,
        String description,
        AttachmentType type
    ) {
        this.name = name;
        this.description = description;
        this.type = type;
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

    public void updateWithOldData(AttachmentEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }
}
