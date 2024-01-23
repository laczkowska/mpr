package org.example.repository;

import org.example.data.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> findByCategory(String category);

    @Query("SELECT b FROM Book b WHERE b.price < :maxPrice")
    List<Book> findBooksCheaperThan(@Param("maxPrice") double maxPrice);

    @Modifying
    @Query("UPDATE Book b SET b.quantityInStock = b.quantityInStock - :quantity WHERE b.id = :bookId")
    void decrementStock(@Param("bookId") Long bookId, @Param("quantity") int quantity);
}


