package com.epam.lab.service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.model.Tag;

public interface TagService extends BaseService<TagDto> {
    Tag convertToEntity(TagDto tagDto);

    TagDto convertToDto(Tag tag);
}
