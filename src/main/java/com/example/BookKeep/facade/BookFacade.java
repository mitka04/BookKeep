package com.example.BookKeep.facade;

import com.example.BookKeep.dto.BookDTO;

import java.util.List;

public interface BookFacade {

    BookDTO createBook(BookDTO bookDTO);

    List<BookDTO> getAllBooks();
}
