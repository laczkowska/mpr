package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class BookDto {
    private Long id;
    private String title;
    private String genre;
    private AuthorDto author;
    private boolean available;

    // Add getters and setters as needed

    public BookDto(Long id, String title, String genre, AuthorDto author, boolean available) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.available = available;
    }
}
