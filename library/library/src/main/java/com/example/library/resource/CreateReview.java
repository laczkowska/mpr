package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateReview {
    private int stars;
    private Long associatedBookId; // Assuming associatedBookId is used to associate the review with a book
}
