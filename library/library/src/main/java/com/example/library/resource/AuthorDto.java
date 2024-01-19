package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class AuthorDto {
    private Long id;
    private String name;

    // Add getters and setters as needed

    public AuthorDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
