package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;

import com.fredlecoat.erp_freelance.domain.entities.values.AttachmentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
}
