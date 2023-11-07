package com.example.BookKeep.controller;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.facade.AuthorFacadeImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorFacadeImpl authorFacadeImpl;

    @Autowired
    public AuthorController(AuthorFacadeImpl authorFacadeImpl){
        this.authorFacadeImpl = authorFacadeImpl;
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO){
        return authorFacadeImpl.createAuthor(authorDTO);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors(){
        return authorFacadeImpl.getAllAuthors();
    }
}
