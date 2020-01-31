package com.epam.lab.repository;

import com.epam.lab.model.Entity;


import java.util.List;

public interface BaseDao<T extends Entity> {
    T getEntityById(long id);
    List<T> getAllEntities();
    boolean deleteEntity(T t);
    boolean updateEntity(T t);
    boolean createEntity(T t);
}
