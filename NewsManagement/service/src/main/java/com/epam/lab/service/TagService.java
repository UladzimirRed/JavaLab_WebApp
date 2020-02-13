package com.epam.lab.service;

import com.epam.lab.dto.TagDto;
import com.epam.lab.exception.ServiceException;

import java.util.List;

public interface TagService extends BaseService<TagDto>{
    List<TagDto> showAllDto();
    TagDto showDtoById(Long tagId);
    boolean saveDto(TagDto tagDto);
    TagDto editDto(TagDto tagDto) throws ServiceException;
    boolean removeDto(Long tagId);
}
