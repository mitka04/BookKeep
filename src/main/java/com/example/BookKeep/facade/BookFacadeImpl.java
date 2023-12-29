package com.example.BookKeep.facade;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.mapper.AuthorMapper;
import com.example.BookKeep.mapper.BookMapper;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.model.Book;
import com.example.BookKeep.service.AuthorService;
import com.example.BookKeep.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookFacadeImpl implements BookFacade{
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookMapper bookMapper;
    private final AuthorMapper authorMapper;

    @Autowired
    public BookFacadeImpl(BookService bookService, AuthorService authorService, BookMapper bookMapper, AuthorMapper authorMapper){
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookMapper = bookMapper;
        this.authorMapper = authorMapper;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO){
        Author author = authorMapper.mapToAuthor(bookDTO.getAuthor());
        author = authorService.createAuthor(author);
        Book book = bookMapper.mapToBook(bookDTO);
        book.setAuthor(author);
        book = bookService.createBook(book);
        return bookMapper.mapToBookDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return books.stream().map(bookMapper::mapToBookDTO).collect(Collectors.toList());
    }

}
