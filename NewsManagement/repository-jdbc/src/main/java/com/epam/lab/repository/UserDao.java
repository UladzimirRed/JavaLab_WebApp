package com.epam.lab.repository;

import com.epam.lab.model.User;

import java.util.List;

public interface UserDao extends BaseDao<User> {
    User getEntityById(Long id);

    List<User> getAllEntities();

    boolean deleteEntity(Long id);

    boolean updateEntity(User user);

    boolean createEntity(User user);
}
