package com.epam.lab.service;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.model.News;

import java.util.List;

public interface NewsService extends BaseService<NewsDto> {
    List<NewsDto> showAllDto();

    NewsDto showDtoById(Long newsId);

    boolean saveDto(NewsDto newsDto);

    NewsDto editDto(NewsDto newsDto) throws ServiceException;

    boolean removeDto(Long newsId);

    List<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria);

    boolean editTitle(News news);

    boolean editShortText(News news);

    boolean editFullText(News news);
}
