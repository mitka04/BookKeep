package com.example.BookKeep.mapper;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.model.Author;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapperImpl implements AuthorMapper{
    @Override
    public AuthorDTO mapToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }

    @Override
    public Author mapToAuthor(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        return author;
    }
}
