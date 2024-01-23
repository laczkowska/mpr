package org.example.service;

import jakarta.transaction.Transactional;
import org.example.data.Book;
import org.example.dto.BookDTO;
import org.example.dto.BookMapper;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    @Autowired
    public BookService(BookRepository bookRepository, BookMapper bookMapper) {
        this.bookRepository = bookRepository;
        this.bookMapper = bookMapper;
    }
    public BookDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Nie znaleziono książki o ID: " + id));
        return bookMapper.bookToBookDTO(book);
    }
    public List<BookDTO> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("Brak dostępnych książek.");
        }
        return books.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }

    public List<BookDTO> getAllBooksDTO() {
        List<Book> books = bookRepository.findAll();
        if (books.isEmpty()) {
            throw new ResourceNotFoundException("Brak dostępnych książek.");
        }
        return books.stream().map(bookMapper::bookToBookDTO).collect(Collectors.toList());
    }


}
