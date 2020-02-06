package com.epam.lab.mapper;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.dto.NewsDto;
import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Author;
import com.epam.lab.model.News;
import com.epam.lab.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class NewsModelMapper {
    private ModelMapper modelMapper;
    private AuthorModelMapper authorModelMapper;
    private TagModelMapper tagModelMapper;

    @Autowired
    public NewsModelMapper(ModelMapper modelMapper, AuthorModelMapper authorModelMapper, TagModelMapper tagModelMapper) {
        this.modelMapper = modelMapper;
        this.authorModelMapper = authorModelMapper;
        this.tagModelMapper = tagModelMapper;
    }

    public NewsDto convertToDto (News news, Author author, List<Tag> tags){
        NewsDto newsDto = modelMapper.map(news, NewsDto.class);
//        newsDto.setNewsId(news.getNewsId());
        newsDto.setTitle(news.getTitle());
        newsDto.setShortText(news.getShortText());
        newsDto.setFullText(news.getFullText());
        newsDto.setCreationDate(news.getCreationDate());
        newsDto.setModificationDate(news.getModificationDate());
        newsDto.setAuthorDto(authorModelMapper.convertToDto(author));
        newsDto.setTags(tags);
        return newsDto;
    }

    public News convertToEntity(NewsDto newsDto){
        News news = modelMapper.map(newsDto, News.class);
        news.setTitle(newsDto.getTitle());
        news.setShortText(newsDto.getShortText());
        news.setFullText(newsDto.getFullText());
        news.setCreationDate(news.getCreationDate());
        news.setModificationDate(news.getModificationDate());
        return news;
    }
}
