package com.epam.lab.repository;

import com.epam.lab.model.AbstractEntity;

import java.util.List;

public interface BaseRepository<T extends AbstractEntity> {

    T getEntityById(Long id);

    List<T> getAllEntities();

    boolean deleteEntity(Long id);

    boolean updateEntity(T t);

    boolean createEntity(T t);
}
