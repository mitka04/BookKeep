package com.example.BookKeep.facade;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.exception.AuthorNotFoundException;
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

    @Autowired
    public BookFacadeImpl(BookService bookService, AuthorService authorService, BookMapper bookMapper){
        this.bookService = bookService;
        this.authorService = authorService;
        this.bookMapper = bookMapper;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO){
        Author author = authorService.getAuthorById(bookDTO.getAuthor().getId());
        if (author == null){
            author = new Author();
            author.setId(bookDTO.getAuthor().getId());
            author.setName(bookDTO.getAuthor().getName());
            author = authorService.createAuthor(author);
        }else{
            throw new AuthorNotFoundException("Автор с ID " + bookDTO.getAuthor().getId() + " уже существует");
        }
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
