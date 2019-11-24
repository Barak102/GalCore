package com.gal.db.repository;

import java.io.IOException;
import java.util.Collection;

public interface Repository<T> {
    T getById(int id) throws IOException;
    int save(T obj) throws IOException;
    void update(T obj) throws IOException;
    void delete(int id) throws IOException;
    Collection<T> getAll();
    Collection<T> getByIds(int[] ids) throws IOException;
}
