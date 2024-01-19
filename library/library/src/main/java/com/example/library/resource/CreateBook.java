package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBook {
    private String title;
    private String genre;
    private Long authorId; // Assuming authorId is used to associate the book with an author
}
