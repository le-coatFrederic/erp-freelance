package com.fredlecoat.erp_freelance.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fredlecoat.erp_freelance.domain.entities.TaskStackEntity;

@Repository
public interface TaskStackRepository extends JpaRepository<TaskStackEntity, Long> {

}
