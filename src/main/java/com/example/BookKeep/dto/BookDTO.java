package com.example.BookKeep.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookDTO {
    private String title;
    private String isbn;
    private AuthorDTO author;
}
