package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;

import java.util.List;

public interface NewsService {
    List<NewsDto> showAllNews ();
    NewsDto showNewsById(Long id);
    boolean saveNews(NewsDto newsDto);
    boolean editNews(NewsDto newsDto);
    boolean removeNews(Long id);
}
