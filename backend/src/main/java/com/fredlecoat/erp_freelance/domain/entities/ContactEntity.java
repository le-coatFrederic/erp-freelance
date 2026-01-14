package com.fredlecoat.erp_freelance.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Setter
@Getter
public class ContactEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstname;

    private String lastname;

    private String job;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private String phone;

    private String linkedin;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

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

    
}
