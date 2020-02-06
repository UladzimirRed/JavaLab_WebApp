package com.epam.lab.service.impl;

import com.epam.lab.dto.TagDto;
import com.epam.lab.mapper.TagModelMapper;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TagServiceImpl implements TagService {
    private TagDao tagDao;
    private TagModelMapper tagModelMapper;

    @Autowired
    public TagServiceImpl(TagDao tagDao, TagModelMapper tagModelMapper) {
        this.tagDao = tagDao;
        this.tagModelMapper = tagModelMapper;
    }

    @Override
    public List<TagDto> showAllTags() {
        return tagDao.getAllEntities().stream().map(tagModelMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TagDto showTagById(Long id) {
        return tagModelMapper.convertToDto(tagDao.getEntityById(id));
    }

    @Override
    public boolean saveTag(TagDto tagDto) {
        return tagDao.createEntity(tagModelMapper.convertToEntity(tagDto));
    }

    @Override
    public boolean editTag(TagDto tagDto) {
        return tagDao.updateEntity(tagModelMapper.convertToEntity(tagDto));
    }

    @Override
    public boolean removeTag(Long id) {
        return tagDao.deleteEntity(id);
    }
}
