package com.epam.lab.jpaservice;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;

import java.util.List;

public interface NewsService extends BaseService<NewsDto> {
    List<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria);
}
