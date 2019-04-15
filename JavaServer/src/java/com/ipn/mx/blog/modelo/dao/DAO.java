package com.ipn.mx.blog.modelo.dao;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author andres
 * @param <T>
 */
public interface DAO<T> {
    public void create(T t) throws SQLException;
    public void update(T t) throws SQLException;
    public void delete(T t) throws SQLException;
    public T read(T t) throws SQLException;
    public List<T> readAll() throws SQLException;
}
