package service;

import com.epam.lab.dto.AuthorDto;
import com.epam.lab.exception.ServiceException;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class AuthorServiceImplTest {
    private AuthorService authorService;
    private static final Long EXISTENT_ID = 1L;
    private static final Long NONEXISTENT_ID = 999L;
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
    void showAllDtoThrowsException() {
        when(authorRepository.getAllEntities()).thenThrow(new ServiceException());
        assertThrows(ServiceException.class, () -> authorRepository.getAllEntities());
    }

    @Test
    void showDtoById() {
        String authorName = "Ben";
        String authorSurname = "Glen";
        when(authorRepository.getEntityById(EXISTENT_ID)).thenReturn(new Author(authorName, authorSurname));
        AuthorDto authorDto = authorService.showDtoById(EXISTENT_ID);
        assertEquals(authorName, authorDto.getAuthorName());
        assertEquals(authorSurname, authorDto.getAuthorSurname());
    }

    @Test
    public void showEntityByIdThrowsException() {
        when(authorRepository.getEntityById(NONEXISTENT_ID)).thenThrow(new ServiceException("Author with ID: 999 was not found"));
        assertThrows(ServiceException.class, () -> authorService.showDtoById(NONEXISTENT_ID));
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
    void saveDtoThrowsException() {
        Author author = new Author();
        AuthorDto authorDto = new AuthorDto();
        when(authorRepository.createEntity(author)).thenReturn(true);
        assertThrows(NullPointerException.class, () -> authorService.saveDto(authorDto));
    }

    @Test
    void editDto() {
        Author author = new Author();
        author.setId(EXISTENT_ID);
        author.setAuthorName("updName");
        author.setAuthorSurname("updSurname");
        AuthorDto authorDto = new AuthorDto();
        authorDto.setId(EXISTENT_ID);
        authorDto.setAuthorName("updName");
        authorDto.setAuthorSurname("updSurname");
        when(authorRepository.updateEntity(author)).thenReturn(true);
        when(authorRepository.getEntityById(EXISTENT_ID)).thenReturn(author);
        AuthorDto updatedAuthorDto = authorService.editDto(authorDto);
        assertEquals("updName", updatedAuthorDto.getAuthorName());
    }


    @Test
    void removeDto() {
        Long authorId = 1L;
        when(authorRepository.deleteEntity(authorId)).thenReturn(true);
        assertTrue(authorService.removeDto(authorId));
    }
}
