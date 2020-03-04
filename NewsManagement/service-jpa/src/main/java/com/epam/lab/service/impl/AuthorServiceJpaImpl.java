package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorServiceJpaImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorServiceJpaImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public Set<AuthorDto> showAllDto() {
        Set<Author> authors = authorRepository.getAllEntities();
        if (authors.stream().findAny().orElse(null) == null) {
            throw new ServiceException("List of authors was not founded");
        }
        return authors.stream().map(this::convertToDto).collect(Collectors.toSet());
    }

    @Override
    public AuthorDto showDtoById(Long authorId) {
        Author author = authorRepository.getEntityById(authorId);
        if (author != null) {
            return modelMapper.map(author, AuthorDto.class);
        } else {
            throw new ServiceException("Author with ID: " + authorId + " was not found");
        }
    }

    @Override
    public boolean saveDto(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        if (authorRepository.createEntity(author)) {
            return true;
        } else throw new ServiceException("Author was not create");
    }

    @Override
    public AuthorDto editDto(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        if (authorRepository.updateEntity(author)) {
            return convertToDto(authorRepository.getEntityById(authorDto.getId()));
        } else throw new ServiceException("Author was not updated");
    }

    @Override
    public boolean removeDto(Long authorId) {
        if (authorRepository.deleteEntity(authorId)) {
            return true;
        } else throw new ServiceException("Author with ID: " + authorId + " was not delete");
    }

    public Author convertToEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }

    public AuthorDto convertToDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }
}
