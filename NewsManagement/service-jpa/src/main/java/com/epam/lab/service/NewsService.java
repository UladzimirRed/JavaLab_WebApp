package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.NewsSearchCriteria;
import com.epam.lab.model.News;

import java.util.Set;

public interface NewsService extends BaseService<NewsDto> {
    Set<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria);

    News convertToEntity(NewsDto newsDto);

    NewsDto convertToDto(News news);
}
