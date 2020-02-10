package com.epam.lab.mapper;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class TagModelMapper {
    private ModelMapper modelMapper;

    @Autowired
    public TagModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public TagDto convertToDto(Tag tag) {
        TagDto tagDto = modelMapper.map(tag, TagDto.class);
        tagDto.setTagId(tag.getTagId());
        tagDto.setTagName(tag.getTagName());
        return tagDto;
    }

    public Tag convertToEntity(TagDto tagDto) {
        Tag tag = modelMapper.map(tagDto, Tag.class);
        tag.setTagId(tagDto.getTagId());
        tag.setTagName(tagDto.getTagName());
        return tag;
    }
}
