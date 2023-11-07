package com.example.BookKeep.service;

import com.example.BookKeep.model.Book;

import java.util.List;

public interface BookService {

    Book createBook(Book book);

    List<Book> getAllBooks();
}
