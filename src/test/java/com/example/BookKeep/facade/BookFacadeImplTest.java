package com.example.BookKeep.facade;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.model.Book;
import com.example.BookKeep.service.AuthorService;
import com.example.BookKeep.service.BookService;
import com.example.BookKeep.util.AuthorMappingUtil;
import com.example.BookKeep.util.BookMappingUtil;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {BookFacadeImpl.class})
@ExtendWith(SpringExtension.class)
class BookFacadeImplTest {
    @MockBean
    private AuthorMappingUtil authorMappingUtil;

    @MockBean
    private AuthorService authorService;

    @Autowired
    private BookFacadeImpl bookFacadeImpl;

    @MockBean
    private BookMappingUtil bookMappingUtil;

    @MockBean
    private BookService bookService;

    /**
     * Method under test: {@link BookFacadeImpl#createBook(BookDTO)}
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
        when(bookService.createBook(Mockito.<Book>any())).thenReturn(book);

        Author author2 = new Author();
        author2.setId(1L);
        author2.setName("Name");
        when(authorService.createAuthor(Mockito.<Author>any())).thenReturn(author2);

        Author author3 = new Author();
        author3.setId(1L);
        author3.setName("Name");

        Book book2 = new Book();
        book2.setAuthor(author3);
        book2.setId(1L);
        book2.setIsbn("Isbn");
        book2.setTitle("Dr");

        AuthorDTO author4 = new AuthorDTO();
        author4.setName("Name");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setAuthor(author4);
        bookDTO.setIsbn("Isbn");
        bookDTO.setTitle("Dr");
        when(bookMappingUtil.mapToBookDTO(Mockito.<Book>any())).thenReturn(bookDTO);
        when(bookMappingUtil.mapToBook(Mockito.<BookDTO>any())).thenReturn(book2);

        Author author5 = new Author();
        author5.setId(1L);
        author5.setName("Name");
        when(authorMappingUtil.mapToAuthor(Mockito.<AuthorDTO>any())).thenReturn(author5);

        AuthorDTO author6 = new AuthorDTO();
        author6.setName("Name");

        BookDTO bookDTO2 = new BookDTO();
        bookDTO2.setAuthor(author6);
        bookDTO2.setIsbn("Isbn");
        bookDTO2.setTitle("Dr");

        // Act
        BookDTO actualCreateBookResult = bookFacadeImpl.createBook(bookDTO2);

        // Assert
        verify(authorService).createAuthor(Mockito.<Author>any());
        verify(bookService).createBook(Mockito.<Book>any());
        verify(authorMappingUtil).mapToAuthor(Mockito.<AuthorDTO>any());
        verify(bookMappingUtil).mapToBook(Mockito.<BookDTO>any());
        verify(bookMappingUtil).mapToBookDTO(Mockito.<Book>any());
        assertSame(bookDTO, actualCreateBookResult);
    }

    /**
     * Method under test: {@link BookFacadeImpl#getAllBooks()}
     */
    @Test
    void testGetAllBooks() {
        // Arrange
        when(bookService.getAllBooks()).thenReturn(new ArrayList<>());
        ArrayList<BookDTO> bookDTOList = new ArrayList<>();
        when(bookMappingUtil.mapToBookDTOList(Mockito.<List<Book>>any())).thenReturn(bookDTOList);

        // Act
        List<BookDTO> actualAllBooks = bookFacadeImpl.getAllBooks();

        // Assert
        verify(bookService).getAllBooks();
        verify(bookMappingUtil).mapToBookDTOList(Mockito.<List<Book>>any());
        assertTrue(actualAllBooks.isEmpty());
        assertSame(bookDTOList, actualAllBooks);
    }
}
