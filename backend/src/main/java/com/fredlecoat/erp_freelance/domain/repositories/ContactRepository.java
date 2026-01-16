package com.fredlecoat.erp_freelance.domain.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.fredlecoat.erp_freelance.domain.entities.CompanyEntity;
import com.fredlecoat.erp_freelance.domain.entities.ContactEntity;

@Repository
public interface ContactRepository extends JpaRepository<ContactEntity, Long> {
    public List<ContactEntity> findByFirstnameLike(String firstname);
    public List<ContactEntity> findByLastnameLike(String lastname);
    public List<ContactEntity> findByCompany(CompanyEntity company);

    @Query("SELECT DISTINCT c FROM ContactEntity c " +
           "WHERE c.id NOT IN (" +
           "  SELECT DISTINCT t.contact.id FROM TaskEntity t " +
           "  JOIN TaskStackEntity ts ON t.taskStack.id = ts.id " +
           "  WHERE ts.isCompleted = false" +
           ") " +
           "ORDER BY c.lastname, c.firstname")
    public List<ContactEntity> findContactsWithoutActiveTasks();
}