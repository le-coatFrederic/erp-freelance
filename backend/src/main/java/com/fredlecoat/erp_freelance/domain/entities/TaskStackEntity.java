package com.fredlecoat.erp_freelance.domain.entities;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "task_stacks")
public class TaskStackEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(mappedBy = "taskStack", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<TaskEntity> tasks = new HashSet<>();

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = false)
    private Boolean isCompleted = false;

    @Column(nullable = false)
    private Instant createdOn;

    @Column(nullable = false)
    private Instant updatedOn;

    public TaskStackEntity(String name, String description) {
        this.name = name;
        this.description = description;
        this.isCompleted = false;
    }

    public TaskStackEntity(String name, String description, Boolean isCompleted) {
        this.name = name;
        this.description = description;
        this.isCompleted = isCompleted != null ? isCompleted : false;
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

    public void updateWithOldData(TaskStackEntity entity) {
        this.id = entity.getId();
        this.createdOn = entity.getCreatedOn();
    }
}