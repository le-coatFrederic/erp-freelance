package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
@Table(name = "contacts")
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstname;

    @Column(nullable = false)
    private String lastname;

    @Column(nullable = false)
    private String job;

    private String email;

    private String phone;

    @Column(nullable = false)
    private String linkedin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    public ContactEntity(
        String firstname, 
        String lastname, 
        String job,
        String email, 
        String phone,
        String linkedin, 
        CompanyEntity company
    ) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.job = job;
        this.email = email;
        this.linkedin = linkedin;
        this.phone = phone;
        this.company = company;
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

    public void updateWithOldData(ContactEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }
    
}
