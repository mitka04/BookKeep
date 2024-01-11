package com.example.BookKeep.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.BookKeep.model.Author;
import com.example.BookKeep.model.Book;
import com.example.BookKeep.repository.BookRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookServiceImpl.class})
@ExtendWith(SpringExtension.class)
class BookServiceImplTest {
    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private BookServiceImpl bookServiceImpl;

    /**
     * Method under test: {@link BookServiceImpl#createBook(Book)}
     */
    @Test
    void testCreateBook() {
        // Arrange
        Author author = new Author();
        author.setId(1L);
        author.setName("Name");

        Book book = new Book();
        book.setAuthor(author);
        book.setId(1L);
        book.setIsbn("Isbn");
        book.setTitle("Dr");
        when(bookRepository.save(Mockito.<Book>any())).thenReturn(book);

        Author author2 = new Author();
        author2.setId(1L);
        author2.setName("Name");

        Book book2 = new Book();
        book2.setAuthor(author2);
        book2.setId(1L);
        book2.setIsbn("Isbn");
        book2.setTitle("Dr");

        // Act
        Book actualCreateBookResult = bookServiceImpl.createBook(book2);

        // Assert
        verify(bookRepository).save(Mockito.<Book>any());
        assertSame(book, actualCreateBookResult);
    }

    /**
     * Method under test: {@link BookServiceImpl#getAllBooks()}
     */
    @Test
    void testGetAllBooks() {
        // Arrange
        ArrayList<Book> bookList = new ArrayList<>();
        when(bookRepository.findAll()).thenReturn(bookList);

        // Act
        List<Book> actualAllBooks = bookServiceImpl.getAllBooks();

        // Assert
        verify(bookRepository).findAll();
        assertTrue(actualAllBooks.isEmpty());
        assertSame(bookList, actualAllBooks);
    }
}
