package com.example.BookKeep.facade;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.model.Book;
import com.example.BookKeep.service.BookServiceImpl;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class BookFacadeTest {

    @LocalServerPort
    private int port;

    @Autowired
    private BookFacade bookFacade;

    @InjectMocks
    private BookFacadeImpl bookFacadeImpl;

    @Mock
    private BookServiceImpl bookServiceImpl;

    @BeforeEach
    public void setUp() {
        // Инициализируйте вашу базу данных или любые необходимые настройки перед выполнением тестов.
    }

    @Test
    public void testCreateBook() {
        // Preparing data for author
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(1L);
        authorDTO.setName("Authors name");

        // Preparing data for book
        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Books name");
        bookDTO.setIsbn("Books ISBN");

        bookDTO.setAuthor(authorDTO);

        // Creating book from facade
        BookDTO createdBook = bookFacade.createBook(bookDTO);

        // Checking if book was created successfully
        assertNotNull(createdBook.getId());
        assertEquals("Books name", createdBook.getTitle());
        assertEquals("Books ISBN", createdBook.getIsbn());
        assertEquals("1", createdBook.getAuthor().getId().toString());

        // Using RestAssured for making HTTP-request and testing REST-controller
        given()
                .port(port)
                .contentType(JSON)
                .when()
                .get("/books")
                .then()
                .statusCode(200) // Should return 200 (OK)
                .body("size()", equalTo(1)) // Should return 1 book
                .body("[0].title", equalTo("Books name"))
                .body("[0].isbn", equalTo("Books ISBN"))
                .body("[0].author.id", equalTo(createdBook.getAuthor().getId().intValue()))
                .body("[0].author.name", equalTo("Authors name"));
    }

    @Test
    public void testGetAllBooksWhenDataExists(){
        Book book1 = new Book();
        book1.setId(1L);
        book1.setTitle("Book 1");

        Book book2 = new Book();
        book2.setId(2L);
        book2.setTitle("Book 2");

        when(bookServiceImpl.getAllBooks()).thenReturn(Arrays.asList(book1, book2));

        List<BookDTO> bookDTOs = bookFacade.getAllBooks();

    }
}
