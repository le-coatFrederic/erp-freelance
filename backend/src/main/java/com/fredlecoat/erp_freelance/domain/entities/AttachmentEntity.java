package com.fredlecoat.erp_freelance.domain.entities;

import com.fredlecoat.erp_freelance.domain.entities.values.AttachmentType;

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

    private String name;

    private String description;

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
