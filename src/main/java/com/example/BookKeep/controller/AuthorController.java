package com.example.BookKeep.controller;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.facade.AuthorFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    private final AuthorFacade authorFacade;

    @Autowired
    public AuthorController(AuthorFacade authorFacade){
        this.authorFacade = authorFacade;
    }

    @PostMapping
    public AuthorDTO createAuthor(@RequestBody AuthorDTO authorDTO){
        return authorFacade.createAuthor(authorDTO);
    }

    @GetMapping
    public List<AuthorDTO> getAllAuthors(){
        return authorFacade.getAllAuthors();
    }
}
