package com.epam.lab.repository;

import com.epam.lab.model.News;

import java.util.List;

public interface NewsDao {
    News getNewsById(long id);
    List<News> getAllNews();
    boolean deleteNews(News news);
    boolean updateNews(News news);
    boolean createNews(News news);
}