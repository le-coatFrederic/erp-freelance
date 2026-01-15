package com.fredlecoat.erp_freelance.domain.services;

import java.util.List;

public interface EntityCrudService<T> {
    public List<T> getAll();
    public T create(T entity);
    public T update(T entity, Long id);
    public T getById(Long id);
    public void delete(Long id);
}
