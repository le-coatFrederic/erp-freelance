package com.fredlecoat.freelanceerp.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredlecoat.freelanceerp.domain.values.ServiceType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String description;

    @Enumerated(value = EnumType.STRING)
    @Column(nullable = false)
    private ServiceType type;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private String unit;

    @OneToMany(mappedBy = "service")
    private Set<QuoteLine> quoteLines;

    @OneToMany(mappedBy = "service")
    private Set<InvoiceLine> invoiceLines;
}
