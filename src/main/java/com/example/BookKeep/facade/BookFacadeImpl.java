package com.example.BookKeep.facade;

import com.example.BookKeep.dto.AuthorDTO;
import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.exception.AuthorNotFoundException;
import com.example.BookKeep.model.Author;
import com.example.BookKeep.model.Book;
import com.example.BookKeep.service.AuthorServiceImpl;
import com.example.BookKeep.service.BookServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookFacadeImpl implements BookFacade{
    private final BookServiceImpl bookServiceImpl;
    private final AuthorServiceImpl authorServiceImpl;

    @Autowired
    public BookFacadeImpl(BookServiceImpl bookServiceImpl, AuthorServiceImpl authorServiceImpl){
        this.bookServiceImpl = bookServiceImpl;
        this.authorServiceImpl = authorServiceImpl;
    }

    @Override
    public BookDTO createBook(BookDTO bookDTO){
        Author author = authorServiceImpl.getAuthorById(bookDTO.getAuthor().getId());
        if (author == null){
            author = new Author();
            author.setId(bookDTO.getAuthor().getId());
            author.setName(bookDTO.getAuthor().getName());
            author = authorServiceImpl.createAuthor(author);
        }else{
            throw new AuthorNotFoundException("Автор с ID " + bookDTO.getAuthor().getId() + " уже существует");
        }
        Book book = new Book();
        book.setTitle(bookDTO.getTitle());
        book.setIsbn(bookDTO.getIsbn());
        book.setAuthor(author);
        book = bookServiceImpl.createBook(book);
        return mapToBookDTO(book);
    }

    @Override
    public List<BookDTO> getAllBooks(){
        List<Book> books = bookServiceImpl.getAllBooks();
        return books.stream().map(this::mapToBookDTO).collect(Collectors.toList());
    }

    private BookDTO mapToBookDTO(Book book) {
        BookDTO bookDTO = new BookDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        bookDTO.setIsbn(book.getIsbn());
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(book.getAuthor().getId());
        authorDTO.setName(book.getAuthor().getName());
        bookDTO.setAuthor(authorDTO);
        return bookDTO;
    }
}
