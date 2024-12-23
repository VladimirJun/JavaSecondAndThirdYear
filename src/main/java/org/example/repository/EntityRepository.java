package org.example.repository;

import java.util.Collection;

public interface EntityRepository<T> {
    Long create(T entity);
    T get(Long id);
    void update(T entity);
    void delete(Long id);
    Collection<T> getAll();
}