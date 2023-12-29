package com.example.BookKeep.controller;

import com.example.BookKeep.dto.AuthorDTO;
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
public class AuthorControllerTest {

    @LocalServerPort
    private static int port;

    @BeforeEach
    public void setUp() {
        RestAssured.port = port;
    }

    @Test
    public void testCreateAndFetchAuthors() {
        // Given
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setName("John Doe");

        // When
        AuthorDTO createdAuthor = given()
                .contentType(ContentType.JSON)
                .body(authorDTO)
                .when()
                .post("/authors")
                .then()
                .statusCode(200)
                .extract()
                .as(AuthorDTO.class);

        // Then
        assertNotNull(createdAuthor);
        assertEquals("John Doe", createdAuthor.getName());

        // Fetching all authors
        List<AuthorDTO> allAuthors = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/authors")
                .then()
                .statusCode(200)
                .extract()
                .jsonPath()
                .getList(".", AuthorDTO.class);

        // Validating that the created author is present in the list
        assertTrue(allAuthors.stream().anyMatch(a -> a.getName().equals(createdAuthor.getName())));
    }
}
