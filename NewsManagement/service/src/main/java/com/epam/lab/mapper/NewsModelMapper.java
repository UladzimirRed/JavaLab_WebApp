package com.epam.lab.mapper;

import com.epam.lab.dto.NewsDto;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class NewsModelMapper {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AuthorModelMapper authorModelMapper;

    public NewsDto convertToDto (News news, Author author){
        NewsDto newsDto = modelMapper.map(news, NewsDto.class);
        newsDto.setNewsId(news.getNewsId());
        newsDto.setTitle(news.getTitle());
        newsDto.setShortText(news.getShortText());
        newsDto.setFullText(news.getFullText());
        newsDto.setCreationDate(news.getCreationDate());
        newsDto.setModificationDate(news.getModificationDate());
        newsDto.setAuthorDto(authorModelMapper.convertToDto(author));
        return newsDto;
    }
}
