package com.epam.lab.service.impl;

import com.epam.lab.dto.TagDto;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.model.Tag;
import com.epam.lab.repository.TagDao;
import com.epam.lab.service.TagService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public List<TagDto> showAllDto() {
        List<Tag> tags = new ArrayList<>(tagDao.getAllEntities());
        if (tags.stream().findAny().orElse(null) == null) {
            throw new ServiceException("List of tags was not found");
        }
        return tags.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public TagDto showDtoById(Long tagId) {
        Tag tag = tagDao.getEntityById(tagId);
        if (tag == null) {
            throw new ServiceException("Tag with ID: " + tagId + " was not found");
        }
        return convertToDto(tag);
    }

    @Override
    public boolean saveDto(TagDto tagDto) {
        Tag tag = convertToEntity(tagDto);
        if (tagDao.createEntity(tag)) {
            return true;
        } else throw new ServiceException("Tag was not create");
    }

    @Override
    @Transactional
    public TagDto editDto(TagDto tagDto) {
        Tag tag = convertToEntity(tagDto);
        if (tagDao.updateEntity(tag)) {
            return convertToDto(tagDao.getEntityById(tag.getId()));
        } else throw new ServiceException("Tag was not updated");
    }

    @Override
    public boolean removeDto(Long tagId) {
        if (tagDao.deleteEntity(tagId)) {
            return true;
        } else throw new ServiceException("Tag with id: " + tagId + " was not delete");
    }

    @Override
    public Tag convertToEntity(TagDto tagDto) {
        return modelMapper.map(tagDto, Tag.class);
    }

    @Override
    public TagDto convertToDto(Tag tag) {
        return modelMapper.map(tag, TagDto.class);
    }
}
