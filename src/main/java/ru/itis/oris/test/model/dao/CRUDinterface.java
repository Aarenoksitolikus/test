package ru.itis.oris.test.model.dao;

public interface CRUDinterface<T> {
    public boolean create(T entity);
    public T get(long id);
    public boolean update(T entity);
    public boolean delete(long id);
}

