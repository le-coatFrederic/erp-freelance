package com.fredlecoat.erp_freelance.domain.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackTransitionEntity;

@Repository
public interface TaskStackTransitionRepository extends JpaRepository<TaskStackTransitionEntity, Long> {

    List<TaskStackTransitionEntity> findBySourceStackId(Long sourceStackId);

    Optional<TaskStackTransitionEntity> findBySourceStackIdAndDestinationStackId(
        Long sourceStackId,
        Long destinationStackId
    );

    boolean existsByIdAndSourceStackIdAndDestinationStackId(
        Long id,
        Long sourceStackId,
        Long destinationStackId
    );
}
