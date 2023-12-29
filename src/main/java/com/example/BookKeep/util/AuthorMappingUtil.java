package com.example.BookKeep.util;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.mapper.AuthorMapper;
import com.example.BookKeep.model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AuthorMappingUtil {

    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorMappingUtil(AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
    }

    public AuthorDTO mapToAuthorDTO(Author author){
        return authorMapper.mapToAuthorDTO(author);
    }

    public Author mapToAuthor(AuthorDTO authorDTO){
        return authorMapper.mapToAuthor(authorDTO);
    }

    public List<AuthorDTO> mapToAuthorDTOList(List<Author> authors){
        return authors.stream()
                .map(authorMapper::mapToAuthorDTO)
                .collect(Collectors.toList());
    }
}
