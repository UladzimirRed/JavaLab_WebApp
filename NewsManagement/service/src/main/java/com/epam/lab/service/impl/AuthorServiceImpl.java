package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.mapper.AuthorModelMapper;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;

    @Autowired
    private AuthorModelMapper authorModelMapper;

    @Override
    public List<AuthorDto> showAllAuthors() {
        return authorDao.getAllEntities().stream().map(authorModelMapper::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto showAuthorById(Long id) {
        return authorModelMapper.convertToDto(authorDao.getEntityById(id));
    }

    @Override
    public boolean saveAuthor(AuthorDto authorDto) {
        return authorDao.createEntity(authorModelMapper.convertToEntity(authorDto));
    }

    @Override
    public boolean editAuthor(AuthorDto authorDto) {
        return authorDao.updateEntity(authorModelMapper.convertToEntity(authorDto));
    }

    @Override
    public boolean removeAuthor(Long id) {
        return authorDao.deleteEntity(id);
    }
}
