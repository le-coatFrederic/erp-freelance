package com.fredlecoat.erp_freelance.domain.entities;

import java.time.LocalDateTime;

import com.fredlecoat.erp_freelance.domain.entities.values.HistoryChangeType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter
@Setter
@Table(name = "history")
public class HistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_template_id", nullable = false)
    private MessageTemplateEntity messageTemplate;

    @Column(nullable = false)
    private HistoryChangeType changeType;

    @Column(nullable = false)
    private LocalDateTime createdOn;

    public HistoryEntity(
        MessageTemplateEntity messageTemplate,
        HistoryChangeType changeType
    ) {
        this.messageTemplate = messageTemplate;
        this.changeType = changeType;
        this.createdOn = LocalDateTime.now();
    }
}