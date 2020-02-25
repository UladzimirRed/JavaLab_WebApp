package com.epam.lab.jpaservice.impl;

import com.epam.lab.dto.TagDto;
import com.epam.lab.jpaservice.TagService;
import com.epam.lab.repository.NewsRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TagServiceImpl implements TagService {
    private NewsRepository newsRepository;
    private ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(NewsRepository newsRepository, ModelMapper modelMapper) {
        this.newsRepository = newsRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> showAllDto() {
        return null;
    }

    @Override
    public TagDto showDtoById(Long id) {
        return null;
    }

    @Override
    public boolean saveDto(TagDto tagDto) {
        return false;
    }

    @Override
    public TagDto editDto(TagDto tagDto) {
        return null;
    }

    @Override
    public boolean removeDto(Long id) {
        return false;
    }
}
