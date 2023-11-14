package com.example.BookKeep.mapper;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapperImpl implements BookMapper{
    private final AuthorMapper authorMapper;

    @Autowired
    public BookMapperImpl(AuthorMapper authorMapper){
        this.authorMapper = authorMapper;
    }

    @Override
    public BookDTO mapToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setAuthor(authorMapper.mapToAuthorDTO(book.getAuthor()));
        return bookDTO;
    }

    @Override
    public Book mapToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(authorMapper.mapToAuthor(bookDTO.getAuthor()));
        return book;
    }
}
