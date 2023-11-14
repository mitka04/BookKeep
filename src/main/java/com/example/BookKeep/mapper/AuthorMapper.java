package com.example.BookKeep.mapper;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.model.Author;

public interface AuthorMapper {
    AuthorDTO mapToAuthorDTO(Author author);
    Author mapToAuthor(AuthorDTO authorDTO);
}
