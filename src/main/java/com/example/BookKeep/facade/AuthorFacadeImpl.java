package com.example.BookKeep.facade;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.service.AuthorService;
import com.example.BookKeep.util.AuthorMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorFacadeImpl implements AuthorFacade{
    private final AuthorService authorService;
    private final AuthorMappingUtil authorMappingUtil;

    @Autowired
    public AuthorFacadeImpl(AuthorService authorService, AuthorMappingUtil authorMappingUtil){
        this.authorService = authorService;
        this.authorMappingUtil = authorMappingUtil;
    }

    @Override
    public AuthorDTO createAuthor(AuthorDTO authorDTO){
        Author author = authorMappingUtil.mapToAuthor(authorDTO);
        author = authorService.createAuthor(author);
        return authorMappingUtil.mapToAuthorDTO(author);
    }

    @Override
    public List<AuthorDTO> getAllAuthors(){
        List<Author> authors = authorService.getAllAuthors();
        return authorMappingUtil.mapToAuthorDTOList(authors);
    }
}
