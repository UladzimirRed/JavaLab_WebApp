package com.epam.lab.service;

import com.epam.lab.dto.TagDto;

import java.util.List;

public interface TagService {
    List<TagDto> showAllTags();
    TagDto showTagById(Long tagId);
    boolean saveTag(TagDto tagDto);
    boolean editTag(TagDto tagDto);
    boolean removeTag(Long tagId);
}
