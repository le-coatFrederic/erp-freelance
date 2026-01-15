package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;
import java.util.Set;

import com.fredlecoat.erp_freelance.domain.entities.values.CompanyCategory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "companies")
public class CompanyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String siret;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private CompanyCategory category;

    private int size;

    @OneToMany(mappedBy = "company")
    private Set<ContactEntity> contacts;

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    public CompanyEntity(String name, String siret, CompanyCategory category, int size) {
        this.name = name;
        this.siret = siret;
        this.category = category;
        this.size = size;
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

    public void updateWithOldData(CompanyEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }
}
