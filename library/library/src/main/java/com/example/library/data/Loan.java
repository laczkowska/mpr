package com.example.library.data;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Setter
public class Loan {
    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private Book book;
    @ManyToOne
    private Customer customer;
    private LocalDate startDate;
    private LocalDate returnDate;

    public Loan(Book book, Customer customer) {
        this.book = book;
        this.customer = customer;
        this.startDate = LocalDate.now();
        this.returnDate = null;
    }

    public void returnBook() {
        this.returnDate = LocalDate.now();
    }
}