package com.fredlecoat.freelanceerp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvoiceLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantity;

    private double unitPrice;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "service_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Service service;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "invoice_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Invoice invoice;
}
