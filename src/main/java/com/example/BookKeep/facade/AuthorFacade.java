package com.example.BookKeep.facade;

import com.example.BookKeep.dto.AuthorDTO;

import java.util.List;

public interface AuthorFacade {

    AuthorDTO createAuthor(AuthorDTO authorDTO);

    List<AuthorDTO> getAllAuthors();
}
