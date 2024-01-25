package org.example.repository;

import org.example.data.Book;
import org.example.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BookRepository bookRepository;

    @BeforeEach
    public void setUp() {
        // Inicjalizacja danych do testów
    }

    @Test
    public void testFindByCategory() {
        // Create test data
        Book book = new Book();
        book.setCategory("testCategory");
        // Save to repository
        Book savedBook = bookRepository.save(book);
        // Test the method
        List<Book> books = bookRepository.findByCategory("testCategory");
        assertTrue(books.size() > 0);
        // Clean up
        bookRepository.delete(savedBook);
    }
}