package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.ServiceException;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorDao authorDao, ModelMapper modelMapper) {
        this.authorDao = authorDao;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDto> showAllDto() {
        List<Author> authors = authorDao.getAllEntities();
        if (authors.stream().findAny().orElse(null) == null) {
            throw new ServiceException("List of authors was not founded");
        }
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto showDtoById(Long authorId) {
        Author author = authorDao.getEntityById(authorId);
        if (author == null) {
            throw new ServiceException("Author with ID: " + authorId + " was not found");
        }
        return convertToDto(author);
    }

    @Override
    public boolean saveDto(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        if (authorDao.createEntity(author)) {
            return true;
        } else throw new ServiceException("Author was not create");
    }

    @Override
    @Transactional
    public AuthorDto editDto(AuthorDto authorDto) {
        Author author = convertToEntity(authorDto);
        if (authorDao.updateEntity(author)) {
            return convertToDto(authorDao.getEntityById(authorDto.getId()));
        } else throw new ServiceException("Author was not updated");
    }

    @Override
    public boolean removeDto(Long authorId) {
        if (authorDao.deleteEntity(authorId)) {
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
