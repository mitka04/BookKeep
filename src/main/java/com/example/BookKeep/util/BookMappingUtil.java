package com.example.BookKeep.util;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.mapper.BookMapper;
import com.example.BookKeep.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class BookMappingUtil {

    private final BookMapper bookMapper;

    @Autowired
    public BookMappingUtil(BookMapper bookMapper){
        this.bookMapper = bookMapper;
    }

    public BookDTO mapToBookDTO(Book book){
        return bookMapper.mapToBookDTO(book);
    }

    public Book mapToBook(BookDTO bookDTO){
        return bookMapper.mapToBook(bookDTO);
    }

    public List<BookDTO> mapToBookDTOList(List<Book> books){
        return books.stream()
                .map(bookMapper::mapToBookDTO)
                .collect(Collectors.toList());
    }
}
