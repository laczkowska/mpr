package com.example.library.mappery;

import com.example.library.data.*;
import com.example.library.resource.*;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class Mapper {
    private final ModelMapper modelMapper;

    public Mapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    // Mapping for Book
    public BookDto convertToDto(Book book) {
        return modelMapper.map(book, BookDto.class);
    }

    public Book convertToEntity(CreateBook createBook) {
        return modelMapper.map(createBook, Book.class);
    }

    // Mapping for Author
    public AuthorDto convertToDto(Author author) {
        return modelMapper.map(author, AuthorDto.class);
    }

    public Author convertToEntity(CreateAuthor createAuthor) {
        return modelMapper.map(createAuthor, Author.class);
    }

    // Mapping for Customer
    public CustomerDto convertToDto(Customer customer) {
        return modelMapper.map(customer, CustomerDto.class);
    }

    public Customer convertToEntity(CreateCustomer createCustomer) {
        return modelMapper.map(createCustomer, Customer.class);}

    // Mapping for Loan
    public LoanDto convertToDto(Loan loan) {
        return modelMapper.map(loan, LoanDto.class);
    }

    public Loan convertToEntity(CreateLoan createLoan) {
        return modelMapper.map(createLoan, Loan.class);
    }

    // Mapping for Review
    public ReviewDto convertToDto(Review review) {
        return modelMapper.map(Review, ReviewDto.class);
    }

    public Review convertToEntity(CreateReview createReview) {
        return modelMapper.map(createReview, Review.class);
    }
}
