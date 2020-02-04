package com.epam.lab.repository;

import com.epam.lab.model.AbstractEntity;


import java.util.List;

public interface BaseDao<T extends AbstractEntity> {
    T getEntityById(long id);
    List<T> getAllEntities();
    boolean deleteEntity(T t);
    boolean updateEntity(T t);
    boolean createEntity(T t);
}
