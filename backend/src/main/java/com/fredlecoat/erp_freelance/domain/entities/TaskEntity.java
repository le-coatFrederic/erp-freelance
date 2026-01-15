package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;

import com.fredlecoat.erp_freelance.domain.entities.values.TaskCategory;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Getter
@Setter
@Table(name = "tasks")
public class TaskEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "message_template_id")
    private MessageTemplateEntity messageTemplate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "contact_id")
    private ContactEntity contact;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "task_stack_id")
    private TaskStackEntity taskStack;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskCategory category;

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    public TaskEntity(
        MessageTemplateEntity messageTemplate,
        ContactEntity contact,
        TaskStackEntity taskStack,
        String name,
        String description,
        TaskCategory category
    ) {
        this.messageTemplate = messageTemplate;
        this.contact = contact;
        this.taskStack = taskStack;
        this.name = name;
        this.description = description;
        this.category = category;
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

    public void updateWithOldData(TaskEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }
}
