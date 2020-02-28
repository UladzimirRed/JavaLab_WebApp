package com.epam.lab.service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;

import java.util.function.Function;

public interface AuthorService extends BaseService<AuthorDto> {
    Author convertToEntity(AuthorDto authorDto);

    AuthorDto convertToDto(Author author);
}
