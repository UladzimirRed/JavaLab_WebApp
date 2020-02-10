package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.ServiceException;

import java.util.List;

public interface NewsService {
    List<NewsDto> showAllNews();
    NewsDto showNewsById(Long newsId);
    boolean saveNews(NewsDto newsDto);
    NewsDto editNews(NewsDto newsDto) throws ServiceException;
    boolean removeNews(Long newsId);
    List<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria);
}
