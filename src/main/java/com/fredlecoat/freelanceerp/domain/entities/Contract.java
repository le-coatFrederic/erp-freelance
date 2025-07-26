package com.fredlecoat.freelanceerp.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.Locale;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Contract {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String contractNumber;

    @Column(nullable = false)
    private Locale startDate;

    @Column(nullable = false)
    private Locale endDate;

    @Column(nullable = false)
    private String terms;

    @OneToMany(mappedBy = "contract")
    private Set<Project> projects;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "quote_id")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Quote quote;
}
