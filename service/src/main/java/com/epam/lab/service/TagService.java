package com.epam.lab.service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.model.Tag;

import java.util.List;

public interface TagService extends BaseService<TagDto> {
    Tag convertToEntity(TagDto tagDto);

    TagDto convertToDto(Tag tag);
}
