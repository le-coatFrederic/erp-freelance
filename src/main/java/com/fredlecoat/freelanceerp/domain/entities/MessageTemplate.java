package com.fredlecoat.freelanceerp.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String subject;

    @Column(nullable = false)
    private String body;

    @OneToMany(mappedBy = "template")
    private Set<Message> messages;

    public MessageTemplate(String subject, String body) {
        this.subject = subject;
        this.body = body;
    }
}
