package com.example.library.service;

import com.example.library.data.*;
import com.example.library.exception.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LibraryService {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final CustomerRepository customerRepository;
    private final LoanRepository loanRepository;

    public LibraryService(BookRepository bookRepository, AuthorRepository authorRepository,
                          CustomerRepository customerRepository, LoanRepository loanRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.customerRepository = customerRepository;
        this.loanRepository = loanRepository;
    }

    public Book addBook(String title, String genre, String authorName) {
        Author author = getOrCreateAuthor(authorName);
        Book book = new Book(title, genre, author);
        return bookRepository.save(book);
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
    }

    public Author addAuthor(String name) {
        Author author = new Author(name);
        return authorRepository.save(author);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Customer addCustomer(String name) {
        Customer customer = new Customer(name);
        return customerRepository.save(customer);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void loanBook(Long bookId, Long customerId) {
        Book book = getBookById(bookId);
        Customer customer = getCustomerById(customerId);

        if (!book.isAvailable()) {
            throw new BookUnavailableException("Book with id " + bookId + " is not available for loan.");
        }

        Loan loan = new Loan(book, customer);
        loanRepository.save(loan);

        book.setAvailable(false);
        bookRepository.save(book);

        customer.setHasCurrentLoan(true);
        customerRepository.save(customer);
    }

    public List<Loan> getAllLoans() {
        return loanRepository.findAll();
    }

    // Add more methods for managing books, authors, customers, and loans
    // ...

    private Author getOrCreateAuthor(String authorName) {
        Optional<Author> existingAuthor = authorRepository.findByName(authorName);
        return existingAuthor.orElseGet(() -> addAuthor(authorName));
    }

    private Customer getCustomerById(Long customerId) {
        return customerRepository.findById(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with id: " + customerId));
    }
}