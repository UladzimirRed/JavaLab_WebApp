package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.ServiceException;

import java.util.List;

public interface AuthorService extends BaseService<AuthorDto> {
    List<AuthorDto> showAllDto();

    AuthorDto showDtoById(Long authorId);

    boolean saveDto(AuthorDto authorDto);

    AuthorDto editDto(AuthorDto authorDto) throws ServiceException;

    boolean removeDto(Long authorId);
}
