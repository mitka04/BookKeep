package com.example.BookKeep.controller;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.facade.BookFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookFacade bookFacade;

    @Autowired
    public BookController (BookFacade bookFacade){
        this.bookFacade = bookFacade;
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookFacade.createBook(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAllBooks(){
        return bookFacade.getAllBooks();
    }
}
