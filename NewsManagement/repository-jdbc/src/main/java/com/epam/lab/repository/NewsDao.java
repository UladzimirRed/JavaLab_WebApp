package com.epam.lab.repository;

import com.epam.lab.model.News;

import java.util.List;
import java.util.Set;

public interface NewsDao extends BaseDao<News>{
    News getEntityById(Long id);
    List<News> getAllEntities();
    boolean deleteEntity(Long id);
    boolean updateEntity(News news);
    boolean createEntity(News news);
    Long findAuthorIdByNewsId(Long id);
    List<Long> findTagsIdByNewsId(Long id);
    boolean linkAuthorIdWithNewsId(Long authorId, Long newsId);
    boolean linkTagIdWithNewsId(Long tagId, Long newsId);
    boolean unlinkAuthorIdFromNewsId(Long newsId);
    boolean unlinkTagIdFromNewsId(Long newsId);
}