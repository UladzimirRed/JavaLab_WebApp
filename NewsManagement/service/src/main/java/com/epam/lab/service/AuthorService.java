package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;


public interface AuthorService extends BaseService<AuthorDto> {
    Author convertToEntity(AuthorDto authorDto);

    AuthorDto convertToDto(Author author);
}
