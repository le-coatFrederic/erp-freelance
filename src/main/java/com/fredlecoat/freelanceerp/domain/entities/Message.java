package com.fredlecoat.freelanceerp.domain.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fredlecoat.freelanceerp.domain.values.MessageStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING)
    private MessageStatus status;

    @Column(nullable = false)
    private LocalDate plannedDate;

    @Column(nullable = false)
    private LocalDate sendDate;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "contact_id")
    @JsonIgnore
    private Contact contact;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "message_template_id")
    @JsonIgnore
    private MessageTemplate messageTemplate;
}
