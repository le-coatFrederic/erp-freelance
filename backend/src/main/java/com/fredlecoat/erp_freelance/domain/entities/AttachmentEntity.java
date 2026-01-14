package com.fredlecoat.erp_freelance.domain.entities;

import com.fredlecoat.erp_freelance.domain.entities.values.AttachmentType;

import jakarta.persistence.Column;
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

    public AttachmentEntity(
        String name,
        String description,
        AttachmentType type
    ) {
        this.name = name;
        this.description = description;
        this.type = type;
    }


}
