package com.example.BookKeep.facade;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.service.AuthorService;
import com.example.BookKeep.util.AuthorMappingUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthorFacadeImpl.class})
@ExtendWith(SpringExtension.class)
class AuthorFacadeImplTest {
    @Autowired
    private AuthorFacadeImpl authorFacadeImpl;

    @MockBean
    private AuthorMappingUtil authorMappingUtil;

    @MockBean
    private AuthorService authorService;

    /**
     * Method under test: {@link AuthorFacadeImpl#createAuthor(AuthorDTO)}
     */
    @Test
    void testCreateAuthor() {
        // Arrange
        Author author = new Author();
        author.setId(1L);
        author.setName("Name");
        when(authorService.createAuthor(Mockito.<Author>any())).thenReturn(author);

        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("Name");

        Author author2 = new Author();
        author2.setId(1L);
        author2.setName("Name");
        when(authorMappingUtil.mapToAuthorDTO(Mockito.<Author>any())).thenReturn(authorDTO);
        when(authorMappingUtil.mapToAuthor(Mockito.<AuthorDTO>any())).thenReturn(author2);

        AuthorDTO authorDTO2 = new AuthorDTO();
        authorDTO2.setName("Name");

        // Act
        AuthorDTO actualCreateAuthorResult = authorFacadeImpl.createAuthor(authorDTO2);

        // Assert
        verify(authorService).createAuthor(Mockito.<Author>any());
        verify(authorMappingUtil).mapToAuthor(Mockito.<AuthorDTO>any());
        verify(authorMappingUtil).mapToAuthorDTO(Mockito.<Author>any());
        assertSame(authorDTO, actualCreateAuthorResult);
    }

    /**
     * Method under test: {@link AuthorFacadeImpl#getAllAuthors()}
     */
    @Test
    void testGetAllAuthors() {
        // Arrange
        when(authorService.getAllAuthors()).thenReturn(new ArrayList<>());
        ArrayList<AuthorDTO> authorDTOList = new ArrayList<>();
        when(authorMappingUtil.mapToAuthorDTOList(Mockito.<List<Author>>any())).thenReturn(authorDTOList);

        // Act
        List<AuthorDTO> actualAllAuthors = authorFacadeImpl.getAllAuthors();

        // Assert
        verify(authorService).getAllAuthors();
        verify(authorMappingUtil).mapToAuthorDTOList(Mockito.<List<Author>>any());
        assertTrue(actualAllAuthors.isEmpty());
        assertSame(authorDTOList, actualAllAuthors);
    }
}

