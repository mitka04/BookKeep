package com.example.BookKeep.facade;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.service.AuthorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorFacadeImpl implements AuthorFacade{
    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public AuthorFacadeImpl(AuthorServiceImpl authorServiceImpl){
        this.authorServiceImpl = authorServiceImpl;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO){
        Author author = new Author();
        author.setName(authorDTO.getName());
        author = authorServiceImpl.createAuthor(author);
        return mapToAuthorDTO(author);
    }

    @Override
    public List<AuthorDTO> getAllAuthors(){
        List<Author> authors = authorServiceImpl.getAllAuthors();
        return authors.stream().map(this::mapToAuthorDTO).collect(Collectors.toList());
    }

    private AuthorDTO mapToAuthorDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        return authorDTO;
    }
}
