package com.example.library.data;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Author {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    public Author(String name) {
        this.name = name;
    }
}