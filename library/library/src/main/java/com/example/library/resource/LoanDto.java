package com.example.library.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

public class LoanDto {
    private Long id;
    private BookDto book;
    private CustomerDto customer;
    private LocalDate startDate;
    private LocalDate returnDate;

    // Add getters and setters as needed

    public LoanDto(Long id, BookDto book, CustomerDto customer, LocalDate startDate, LocalDate returnDate) {
        this.id = id;
        this.book = book;
        this.customer = customer;
        this.startDate = startDate;
        this.returnDate = returnDate;
    }
}
