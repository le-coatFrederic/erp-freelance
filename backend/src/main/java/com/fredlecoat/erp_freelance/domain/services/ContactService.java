package com.fredlecoat.erp_freelance.domain.services;

import java.util.List;

import com.fredlecoat.erp_freelance.domain.entities.ContactEntity;

public interface ContactService extends EntityCrudService<ContactEntity> {
    List<ContactEntity> getAvailableForTask();
}
