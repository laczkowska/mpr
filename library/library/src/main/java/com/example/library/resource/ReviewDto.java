package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ReviewDto {
    private int ratingId;
    private int stars;
    private BookDto associatedBook;

    // Add getters and setters as needed

    public ReviewDto(int ratingId, int stars, BookDto associatedBook) {
        this.ratingId = ratingId;
        this.stars = stars;
        this.associatedBook = associatedBook;
    }
}
