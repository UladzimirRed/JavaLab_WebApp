package com.epam.lab.repository;

import com.epam.lab.model.Tag;

import java.util.List;

public interface TagDao extends BaseDao<Tag> {
    Tag getEntityById(long id);
    List<Tag> getAllEntities();
    boolean deleteEntity(Tag tag);
    boolean updateEntity(Tag tag);
    boolean createEntity(Tag tag);
}
