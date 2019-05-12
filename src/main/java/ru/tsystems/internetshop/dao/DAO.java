package ru.tsystems.internetshop.dao;

import java.util.List;

/**
 * This interface declares CRUD methods for "T" entity class with PK type
 * DAO interfaces should implements this interface
 */
public interface DAO<T, PK> {
    void create(T entity);

    void update(T entity);

    void delete(T entity);

    T findByKey(PK key);

    List<T> findAll();
}
