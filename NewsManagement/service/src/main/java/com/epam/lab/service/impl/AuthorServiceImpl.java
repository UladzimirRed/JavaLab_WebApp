package com.epam.lab.service.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorDao;
import com.epam.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorDao authorDao;

    @Override
    public List<Author> showAllAuthors() {
        return authorDao.getAllEntities();
    }

    @Override
    public Author showAuthorById(long id) {
        return authorDao.getEntityById(id);
    }

    @Override
    public Author saveAuthor(Author author) {
        authorDao.createEntity(author);
        return authorDao.getEntityById(author.getAuthorId());
    }


}
