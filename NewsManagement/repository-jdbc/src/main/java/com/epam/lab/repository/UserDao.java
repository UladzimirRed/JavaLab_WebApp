package com.epam.lab.repository;

import com.epam.lab.model.User;

import java.util.List;

public interface UserDao extends BaseDao<User>{
    User getEntityById(long id);
    List<User> getAllEntities();
    boolean deleteEntity(User user);
    boolean updateEntity(User user);
    boolean createEntity(User user);
}
