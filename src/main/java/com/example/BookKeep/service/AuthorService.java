package com.example.BookKeep.service;

import com.example.BookKeep.model.Author;

import java.util.List;

public interface AuthorService {

    Author createAuthor(Author author);

    Author getAuthorById(Long authorId);

    List<Author> getAllAuthors();
}
