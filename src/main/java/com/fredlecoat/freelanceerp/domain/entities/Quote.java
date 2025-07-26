package com.fredlecoat.freelanceerp.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredlecoat.freelanceerp.domain.values.QuoteStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Quote {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String reference;

    @Column(nullable = false)
    private LocalDateTime creationDate;

    @Column(nullable = false)
    private LocalDateTime validUntil;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private QuoteStatus status;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JsonIgnore
    @JoinColumn(name = "quest_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Quest quest;

    @OneToMany(mappedBy = "quote")
    private Set<Invoice> invoices;

    @OneToMany(mappedBy = "quote")
    private Set<QuoteLine> quoteLines;
}
