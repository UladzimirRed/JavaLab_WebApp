package com.epam.lab.repository;

import com.epam.lab.model.Tag;

import java.util.List;

public interface TagDao extends BaseDao<Tag> {
    Tag getEntityById(Long id);
    List<Tag> getAllEntities();
    boolean deleteEntity(Long id);
    boolean updateEntity(Tag tag);
    boolean createEntity(Tag tag);
}
