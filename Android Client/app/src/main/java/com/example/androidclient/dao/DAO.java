package com.example.androidclient.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    public long create(T t) throws SQLException;
    public int update(T t) throws SQLException;
    public int delete(T t) throws SQLException;
    public T read(T t) throws SQLException;
    public List<T> readAll() throws SQLException;
}

