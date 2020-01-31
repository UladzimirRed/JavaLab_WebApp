package com.epam.lab.repository;

import com.epam.lab.model.News;

import java.util.List;

public interface NewsDao extends BaseDao<News>{
    News getEntityById(long id);
    List<News> getAllEntities();
    boolean deleteEntity(News news);
    boolean updateEntity(News news);
    boolean createEntity(News news);
}