package ru.tsystems.internetshop.dao;

import java.util.List;

public interface DAO<T, PK> {
    void create(T entity);

    void update(T entity);

    void delete(T entity);

    T findByKey(PK key);

    List<T> findAll();
}
