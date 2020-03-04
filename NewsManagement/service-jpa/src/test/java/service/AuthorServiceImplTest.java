package service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.model.Author;
import com.epam.lab.repository.AuthorRepository;
import com.epam.lab.service.AuthorService;
import com.epam.lab.service.impl.AuthorServiceJpaImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class AuthorServiceImplTest {
    AuthorService authorService;
    @Mock
    AuthorRepository authorRepository;
    @Mock
    ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        modelMapper = new ModelMapper();
        authorService = new AuthorServiceJpaImpl(authorRepository, modelMapper);
    }

    @Test
    void showAllDto() {
        Set<Author> authors = new HashSet<>();
        Author author = new Author("Lukas", "Scarsitto");
        Author author2 = new Author("Albert", "Oneill");
        Author author3 = new Author("Wayne", "Wall");
        authors.add(author);
        authors.add(author2);
        authors.add(author3);
        Integer expectedListSize = 3;

        when(authorRepository.getAllEntities()).thenReturn(authors);
        Set<AuthorDto> authorDtos = authorService.showAllDto();

        assertEquals(expectedListSize, authorDtos.size());
    }

    @Test
    void showDtoById() {
        String authorName = "Ben";
        String authorSurname = "Glen";
        when(authorRepository.getEntityById(1L)).thenReturn(new Author(authorName, authorSurname));
        AuthorDto authorDto = authorService.showDtoById(1L);
        assertEquals(authorName, authorDto.getAuthorName());
        assertEquals(authorSurname, authorDto.getAuthorSurname());
    }

    @Test
    void saveDto() {
        String authorName = "Stacy";
        String authorSurname = "Mcmahon";
        AuthorDto authorDto = new AuthorDto();
        authorDto.setAuthorName(authorName);
        authorDto.setAuthorSurname(authorSurname);

        Author author = new Author();
        author.setAuthorName(authorName);
        author.setAuthorSurname(authorSurname);

        when(authorRepository.createEntity(author)).thenReturn(true);
        assertTrue(authorService.saveDto(authorDto));
    }

    @Test
    void removeDto() {
        Long authorId = 1L;
        when(authorRepository.deleteEntity(authorId)).thenReturn(true);
        assertTrue(authorService.removeDto(authorId));
    }
}
