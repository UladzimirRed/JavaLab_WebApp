package com.epam.lab.service.impl;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
import com.epam.lab.service.NewsService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class NewsServiceImpl implements NewsService {
    private NewsRepository newsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }


    @Override
    public List<NewsDto> showAllDto() {
        List<News> news = newsRepository.getAllEntities();
        return news.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public NewsDto showDtoById(Long id) {
        News news = newsRepository.getEntityById(id);
        if (news != null) {
            return modelMapper.map(news, NewsDto.class);
        } else {
            throw new EntityNotFoundException("No news exist for given id");
        }
    }

    @Override
    public boolean saveDto(NewsDto newsDto) {
        return false;
    }

    @Override
    public NewsDto editDto(NewsDto newsDto) {
        return null;
    }

    @Override
    public boolean removeDto(Long id) {
        return false;
    }

    @Override
    public List<NewsDto> searchByCriteria(NewsSearchCriteria searchCriteria) {
        return null;
    }

    @Override
    public boolean editTitle(News news) {
        return false;
    }

    @Override
    public boolean editShortText(News news) {
        return false;
    }

    @Override
    public boolean editFullText(News news) {
        return false;
    }

    public News convertToEntity(NewsDto newsDto) {
        return modelMapper.map(newsDto, News.class);
    }

    public NewsDto convertToDto(News news) {
        return modelMapper.map(news, NewsDto.class);
    }


}
