package com.example.BookKeep.controller;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.facade.BookFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    private final BookFacadeImpl bookFacadeImpl;

    @Autowired
    public BookController (BookFacadeImpl bookFacadeImpl){
        this.bookFacadeImpl = bookFacadeImpl;
    }

    @PostMapping
    public BookDTO createBook(@RequestBody BookDTO bookDTO) {
        return bookFacadeImpl.createBook(bookDTO);
    }

    @GetMapping
    public List<BookDTO> getAllBooks(){
        return bookFacadeImpl.getAllBooks();
    }
}
