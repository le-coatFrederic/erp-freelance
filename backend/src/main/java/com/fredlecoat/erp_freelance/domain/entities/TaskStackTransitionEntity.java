package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;

import com.fredlecoat.erp_freelance.domain.entities.values.TransitionType;

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
@Table(name = "task_stack_transitions")
public class TaskStackTransitionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "source_stack_id", nullable = false)
    private TaskStackEntity sourceStack;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "destination_stack_id", nullable = false)
    private TaskStackEntity destinationStack;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TransitionType transitionType;

    @Column(name = "auto_transition_threshold_hours")
    private Long autoTransitionThresholdHours;

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    public TaskStackTransitionEntity(
        TaskStackEntity sourceStack,
        TaskStackEntity destinationStack,
        TransitionType transitionType,
        Long autoTransitionThresholdHours
    ) {
        this.sourceStack = sourceStack;
        this.destinationStack = destinationStack;
        this.transitionType = transitionType;
        this.autoTransitionThresholdHours = autoTransitionThresholdHours;
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

    public void updateWithOldData(TaskStackTransitionEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }
}
