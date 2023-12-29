package com.example.BookKeep.controller;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.dto.BookDTO;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class BookControllerTest {

    @LocalServerPort
    private static int port;



    @BeforeEach
    public void setUp(){
        RestAssured.port = port;
    }

    @Test
    public void testCreateBook() {
        System.out.println();

        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");

        BookDTO bookDTO = new BookDTO();
        bookDTO.setTitle("Title");
        bookDTO.setIsbn("Isbn");
        bookDTO.setAuthor(authorDTO);

        // When
        BookDTO createdBook = given()
                .contentType(ContentType.JSON)
                .body(bookDTO)
                .when()
                .post("/books")
                .then()
                .statusCode(200)
                .extract()
                .as(BookDTO.class);

        // Then
        assertNotNull(createdBook);
        assertEquals("Title", createdBook.getTitle());
        assertEquals("John Doe", createdBook.getAuthor().getName());

        // Fetching all authors
        List<BookDTO> allBooks = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/books")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", BookDTO.class);

        // Validating that the created author is present in the list
        assertTrue(allBooks.stream().anyMatch(a -> a.getTitle().equals(createdBook.getTitle())));
    }
}