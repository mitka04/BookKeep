package com.example.BookKeep.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.BookKeep.model.Author;
import com.example.BookKeep.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {AuthorServiceImpl.class})
@ExtendWith(SpringExtension.class)
class AuthorServiceImplTest {
    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private AuthorServiceImpl authorServiceImpl;

    /**
     * Method under test: {@link AuthorServiceImpl#createAuthor(Author)}
     */
    @Test
    void testCreateAuthor() {
        // Arrange
        Author author = new Author();
        author.setId(1L);
        author.setName("Name");
        when(authorRepository.save(Mockito.<Author>any())).thenReturn(author);

        Author author2 = new Author();
        author2.setId(1L);
        author2.setName("Name");

        // Act
        Author actualCreateAuthorResult = authorServiceImpl.createAuthor(author2);

        // Assert
        verify(authorRepository).save(Mockito.<Author>any());
        assertSame(author, actualCreateAuthorResult);
    }

    /**
     * Method under test: {@link AuthorServiceImpl#getAuthorById(Long)}
     */
    @Test
    void testGetAuthorById() {
        // Arrange
        Author author = new Author();
        author.setId(1L);
        author.setName("Name");
        Optional<Author> ofResult = Optional.of(author);
        when(authorRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        Author actualAuthorById = authorServiceImpl.getAuthorById(1L);

        // Assert
        verify(authorRepository).findById(Mockito.<Long>any());
        assertSame(author, actualAuthorById);
    }

    /**
     * Method under test: {@link AuthorServiceImpl#getAllAuthors()}
     */
    @Test
    void testGetAllAuthors() {
        // Arrange
        ArrayList<Author> authorList = new ArrayList<>();
        when(authorRepository.findAll()).thenReturn(authorList);

        // Act
        List<Author> actualAllAuthors = authorServiceImpl.getAllAuthors();

        // Assert
        verify(authorRepository).findAll();
        assertTrue(actualAllAuthors.isEmpty());
        assertSame(authorList, actualAllAuthors);
    }
}
