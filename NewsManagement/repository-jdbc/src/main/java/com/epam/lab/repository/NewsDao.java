package com.epam.lab.repository;

import com.epam.lab.model.News;

import java.util.List;

public interface NewsDao extends BaseDao<News>{
    News getEntityById(Long id);
    List<News> getAllEntities();
    boolean deleteEntity(Long id);
    boolean updateEntity(News news);
    boolean createEntity(News news);
    Long findAuthorIdByNewsId(Long id);
}