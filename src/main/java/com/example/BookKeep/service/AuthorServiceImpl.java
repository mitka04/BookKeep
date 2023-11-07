package com.example.BookKeep.service;

import com.example.BookKeep.model.Author;
import com.example.BookKeep.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService{
    private final AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository){
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(Author author){
        return authorRepository.save(author);
    }

    @Override
    public Author getAuthorById(Long authorId) {
        return authorRepository.findById(authorId)
                .orElse(null);
    }

    @Override
    public List<Author> getAllAuthors(){
        return authorRepository.findAll();
    }
}
