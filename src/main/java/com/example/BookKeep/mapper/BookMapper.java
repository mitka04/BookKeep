package com.example.BookKeep.mapper;

import com.example.BookKeep.dto.BookDTO;
import com.example.BookKeep.model.Book;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BookMapper {
    BookDTO mapToBookDTO(Book book);
    Book mapToBook(BookDTO bookDTO);
}
