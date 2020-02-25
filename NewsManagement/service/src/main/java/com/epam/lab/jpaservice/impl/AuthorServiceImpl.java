package com.epam.lab.jpaservice.impl;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.EntityNotFoundException;
import com.epam.lab.jpaservice.AuthorService;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {
    private AuthorRepository authorRepository;
    private ModelMapper modelMapper;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository, ModelMapper modelMapper) {
        this.authorRepository = authorRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<AuthorDto> showAllDto() {
        List<Author> authors = authorRepository.getAllEntities();
        return authors.stream().map(this::convertToDto).collect(Collectors.toList());
    }

    @Override
    public AuthorDto showDtoById(Long id) {
        Author author = authorRepository.getEntityById(id);
        if (author != null){
            return modelMapper.map(author, AuthorDto.class);
        } else {
            throw new EntityNotFoundException("No author exist for given id");
        }
    }

    @Override
    public boolean saveDto(AuthorDto authorDto) {
        return false;
    }

    @Override
    public AuthorDto editDto(AuthorDto authorDto) {
        return null;
    }

    @Override
    public boolean removeDto(Long id) {
        return false;
    }

    public Author convertToEntity(AuthorDto authorDto) {
        return modelMapper.map(authorDto, Author.class);
    }

    public AuthorDto convertToDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }
}
