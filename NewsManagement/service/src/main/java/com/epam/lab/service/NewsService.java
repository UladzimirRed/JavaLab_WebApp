package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.exception.ServiceException;

import java.util.List;

public interface NewsService {
    NewsDto showNewsById(Long id);
    boolean saveNews(NewsDto newsDto);
    boolean editNews(NewsDto newsDto);
    boolean removeNews(Long id);
}
