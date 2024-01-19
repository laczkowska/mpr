package com.example.library.data;

import org.springframework.data.jpa.repository.JpaRepository;

public interface Repository extends JpaRepository<Customer, Long> {
}

public class Review {
    private int ratingId;
    private int stars;
    private Book associatedBook;

    // Konstruktory, gettery i settery

    public StarRating(int ratingId, int stars, Book associatedBook) {
        this.ratingId = ratingId;
        this.stars = stars;
        this.associatedBook = associatedBook;
    }

    // Dodatkowe metody, jeśli są potrzebne
}

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
