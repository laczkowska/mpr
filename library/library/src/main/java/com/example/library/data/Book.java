package com.example.library.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Book {
    @Id
    @GeneratedValue
    private Long id;
    private String title;
    private String genre;
    @ManyToOne
    private Author author;
    private boolean available;

    public Book(String title, String genre, Author author) {
        this.title = title;
        this.genre = genre;
        this.author = author;
        this.available = true;
    }
}