package com.epam.lab.repository;

import com.epam.lab.model.News;

import java.util.List;

public interface NewsDao extends BaseDao<News> {

    boolean updateTitle(String title, Long newsId);

    boolean updateShortText(String shortText, Long newsId);

    boolean updateFullText(String fullText, Long newsId);

    boolean linkAuthorWithNews(Long authorId, Long newsId);

    boolean linkTagWithNews(Long tagId, Long newsId);

    List<News> getEntityBySearchCriteria(String sql);

    boolean unlinkAuthorIdFromNewsId(Long newsId);

    boolean unlinkTagIdFromNewsId(Long newsId);
}