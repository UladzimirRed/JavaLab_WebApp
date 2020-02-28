package com.epam.lab.repository;

import com.epam.lab.model.News;
import com.epam.lab.model.NewsSearchCriteria;

import java.util.Set;

public interface NewsRepository extends BaseRepository<News> {
    Set<News> getEntityBySearchCriteria(NewsSearchCriteria newsSearchCriteria);
}
