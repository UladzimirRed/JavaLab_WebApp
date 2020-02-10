package com.epam.lab.mapper;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class AuthorModelMapper {
    private ModelMapper modelMapper;

    @Autowired
    public AuthorModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public AuthorDto convertToDto (Author author){
        AuthorDto authorDto = modelMapper.map(author, AuthorDto.class);
        authorDto.setAuthorId(author.getAuthorId());
        authorDto.setAuthorName(author.getAuthorName());
        authorDto.setAuthorSurname(author.getAuthorSurname());
        return authorDto;
    }

    public Author convertToEntity(AuthorDto authorDto){
        Author author = modelMapper.map(authorDto, Author.class);
        author.setAuthorName(authorDto.getAuthorName());
        author.setAuthorSurname(authorDto.getAuthorSurname());
        return author;
    }
}
