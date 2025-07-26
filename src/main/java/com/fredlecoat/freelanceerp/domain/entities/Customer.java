package com.fredlecoat.freelanceerp.domain.entities;

import com.fredlecoat.freelanceerp.domain.values.CustomerContactGap;
import com.fredlecoat.freelanceerp.domain.values.CustomerStatus;
import com.fredlecoat.freelanceerp.domain.values.CustomerType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String socialReason;

    @Column(nullable = true)
    private String SIRET;

    @Column(nullable = false)
    private String address;

    @Column(nullable = true)
    private String mail;

    @Column(nullable = true)
    private String phone;

    @Column(nullable = true)
    private String website;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CustomerType type;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CustomerStatus status;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private CustomerContactGap contactSituation;

    @OneToMany(mappedBy = "customer")
    private Set<Contact> contacts;

    @OneToMany(mappedBy = "customer")
    private Set<Quest> quests;

    public Customer(
            String name,
            String socialReason,
            String SIRET,
            String address,
            String mail,
            String phone,
            String website,
            CustomerType type,
            CustomerStatus status,
            CustomerContactGap contactSituation
    ) {
        this.name = name;
        this.socialReason = socialReason;
        this.SIRET = SIRET;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.website = website;
        this.type = type;
        this.status = status;
        this.contactSituation = contactSituation;
    }
}
