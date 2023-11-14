package com.example.BookKeep.facade;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.mapper.AuthorMapper;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorFacadeImpl implements AuthorFacade{
    private final AuthorService authorService;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorFacadeImpl(AuthorService authorService, AuthorMapper authorMapper){
        this.authorService = authorService;
        this.authorMapper = authorMapper;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO){
        Author author = authorMapper.mapToAuthor(authorDTO);
        author = authorService.createAuthor(author);
        return authorMapper.mapToAuthorDTO(author);
    }

    @Override
    public List<AuthorDTO> getAllAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        return authors.stream().map(authorMapper::mapToAuthorDTO).collect(Collectors.toList());
    }
}
