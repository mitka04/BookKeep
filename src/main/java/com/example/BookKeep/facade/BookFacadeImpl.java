package com.example.BookKeep.facade;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.model.Book;
import com.example.BookKeep.service.AuthorService;
import com.example.BookKeep.service.BookService;
import com.example.BookKeep.util.AuthorMappingUtil;
import com.example.BookKeep.util.BookMappingUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookFacadeImpl implements BookFacade{
    private final BookService bookService;
    private final AuthorService authorService;
    private final BookMappingUtil bookMappingUtil;
    private final AuthorMappingUtil authorMappingUtil;

    @Autowired
    public BookFacadeImpl(BookService bookService, AuthorService authorService, BookMappingUtil bookMappingUtil, AuthorMappingUtil authorMappingUtil){
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookMappingUtil = bookMappingUtil;
        this.authorMappingUtil = authorMappingUtil;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO){
        Author author = authorMappingUtil.mapToAuthor(bookDTO.getAuthor());
        author = authorService.createAuthor(author);
        Book book = bookMappingUtil.mapToBook(bookDTO);
        book.setAuthor(author);
        book = bookService.createBook(book);
        return bookMappingUtil.mapToBookDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookService.getAllBooks();
        return bookMappingUtil.mapToBookDTOList(books);
    }

}
