package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.model.News;

import java.util.List;

public interface NewsService extends BaseService<NewsDto> {

    List<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria);

    boolean editTitle(News news);

    boolean editShortText(News news);

    boolean editFullText(News news);

    News convertToEntity(NewsDto newsDto);

    NewsDto convertToDto(News news);
}
