package com.example.library.data;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class Library {
    @Id
    @GeneratedValue
    private UUID id;
    private String name;
    private String location;

    @OneToMany(mappedBy = "library", cascade = CascadeType.ALL)
    private Set<Book> books = new HashSet<>();

    // Constructors, getters, setters, etc.

    public void addBook(Book book) {
        books.add(book);
        book.setLibrary(this);
    }

    public void removeBook(Book book) {
        books.remove(book);
        book.setLibrary(null);
    }
}