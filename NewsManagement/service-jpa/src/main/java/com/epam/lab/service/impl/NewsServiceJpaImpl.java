package com.epam.lab.service.impl;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.NewsSearchCriteria;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class NewsServiceJpaImpl implements NewsService {
    private NewsRepository newsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public NewsServiceJpaImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public Set<NewsDto> showAllDto() {
        Set<News> news = newsRepository.getAllEntities();
        if (news.stream().findAny().orElse(null) == null) {
            throw new ServiceException("Set of news was not founded");
        }
        return news.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    @Override
    public NewsDto showDtoById(Long id) {
        News news = newsRepository.getEntityById(id);
        if (news != null) {
            return convertToDto(news);
        } else {
            throw new EntityNotFoundException("No news exist for given id");
        }
    }

    @Override
    public boolean saveDto(NewsDto newsDto) {
        News news = convertToEntity(newsDto);
        if (newsRepository.createEntity(news)) {
            return true;
        } else {
            throw new ServiceException("News was not create");
        }
    }

    @Override
    @Transactional
    public NewsDto editDto(NewsDto newsDto) {
        News news = convertToEntity(newsDto);
        if (newsRepository.updateEntity(news)) {
            return convertToDto(newsRepository.getEntityById(newsDto.getId()));
        } else {
            throw new ServiceException("News was not updated");
        }
    }

    @Override
    public boolean removeDto(Long id) {
        if (newsRepository.deleteEntity(id)) {
            return true;
        } else throw new ServiceException("News with ID: " + id + " was not delete");
    }

    @Override
    public Set<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria) {
        Set<News> newsBySearchCriteria = newsRepository.getEntityBySearchCriteria(searchCriteria);
        if (newsBySearchCriteria.stream().findAny().orElse(null) == null) {
            throw new ServiceException("Set of news was not founded");
        }
        return newsBySearchCriteria.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    public News convertToEntity(NewsDto newsDto) {
        News news = modelMapper.map(newsDto, News.class);
        news.setAuthors(newsDto.getAuthor());
        return news;
    }

    public NewsDto convertToDto(News news) {
        NewsDto newsDto = modelMapper.map(news, NewsDto.class);
        newsDto.setAuthor(news.getAuthors());
        return newsDto;
    }
}
