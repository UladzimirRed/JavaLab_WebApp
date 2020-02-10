package com.epam.lab.service.impl;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private TagDao tagDao;
    private ModelMapper modelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, ModelMapper modelMapper) {
        this.tagDao = tagDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TagDto> showAllTags() {
        return tagDao.getAllEntities().stream().map(tag -> modelMapper.map(tag, TagDto.class)).collect(Collectors.toList());
    }

    @Override
    public TagDto showTagById(Long tagId) {
        return modelMapper.map(tagDao.getEntityById(tagId), TagDto.class);
    }

    @Override
    public boolean saveTag(TagDto tagDto) {
        return tagDao.createEntity(modelMapper.map(tagDto, Tag.class));
    }

    @Override
    public boolean editTag(TagDto tagDto) {
        return tagDao.updateEntity(modelMapper.map(tagDto, Tag.class));   }

    @Override
    public boolean removeTag(Long tagId) {
        return tagDao.deleteEntity(tagId);
    }
}
