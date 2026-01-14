package com.fredlecoat.erp_freelance.domain.entities;

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

    public CompanyEntity(String name, String siret, CompanyCategory category, int size) {
        this.name = name;
        this.siret = siret;
        this.category = category;
        this.size = size;
    }
}
