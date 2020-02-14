package com.epam.lab.repository;

import com.epam.lab.model.News;

import java.util.List;

public interface NewsDao extends BaseDao<News> {
    News getEntityById(Long newsId);

    List<News> getAllEntities();

    boolean deleteEntity(Long newsId);

    boolean updateEntity(News news);

    boolean createEntity(News news);

    boolean updateTitle(String title, Long newsId);

    boolean updateShortText(String shortText, Long newsId);

    boolean updateFullText(String fullText, Long newsId);

    boolean linkAuthorWithNews(Long authorId, Long newsId);

    boolean linkTagWithNews(Long tagId, Long newsId);

    List<News> getEntityBySearchCriteria(String sql);

    boolean unlinkAuthorIdFromNewsId(Long newsId);

    boolean unlinkTagIdFromNewsId(Long newsId);
}