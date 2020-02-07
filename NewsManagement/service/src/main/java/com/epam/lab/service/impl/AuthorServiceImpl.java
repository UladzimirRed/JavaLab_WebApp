package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.service.AuthorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public List<AuthorDto> showAllAuthors() {
        return authorDao.getAllEntities().stream().map(source -> modelMapper.map(source, AuthorDto.class)).collect(Collectors.toList());
    }

    @Override
    public AuthorDto showAuthorById(Long id) {
        return modelMapper.map(authorDao.getEntityById(id), AuthorDto.class);
    }

    @Override
    public boolean saveAuthor(AuthorDto authorDto) {
        return authorDao.createEntity(modelMapper.map(authorDto, Author.class));
    }

    @Override
    public boolean editAuthor(AuthorDto authorDto) {
        return authorDao.updateEntity(modelMapper.map(authorDto, Author.class));
    }

    @Override
    public boolean removeAuthor(Long id) {
        return authorDao.deleteEntity(id);
    }
}
