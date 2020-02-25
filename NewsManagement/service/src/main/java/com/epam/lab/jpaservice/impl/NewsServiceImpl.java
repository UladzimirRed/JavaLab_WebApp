package com.epam.lab.jpaservice.impl;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.NewsSearchCriteria;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.jpaservice.NewsService;
import com.epam.lab.model.News;
import com.epam.lab.repository.NewsRepository;
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
    public NewsServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper){
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
        if (news != null){
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

    public News convertToEntity(NewsDto newsDto){
        return modelMapper.map(newsDto, News.class);
    }

    public NewsDto convertToDto(News news){
        return modelMapper.map(news, NewsDto.class);
    }


}
