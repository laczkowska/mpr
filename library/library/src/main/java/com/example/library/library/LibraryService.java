import com.example.library.data.Book;
import com.example.library.mappery.Mapper;
import com.example.library.resource.CreateBook;
import com.example.library.resource.BookDto;
import com.example.library.data.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    private final BookRepository bookRepository;
    private final Mapper mapper;

    public LibraryService(BookRepository bookRepository, Mapper mapper) {
        this.bookRepository = bookRepository;
        this.mapper = mapper;
    }

    public void createBook(CreateBook createBook) {
        Book book = mapper.convertToEntity(createBook);
        bookRepository.save(book);
    }

    public BookDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
        return mapper.convertToDto(book);
    }

    public List<BookDto> getAllBooks() {
        List<Book> books = bookRepository.findAll();
        return books.stream()
                .map(mapper::convertToDto)
                .collect(Collectors.toList());
    }

    public void updateBook(Long id, CreateBook createBook) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));

        // Update book properties based on createBook
        existingBook.setTitle(createBook.getTitle());
        existingBook.setGenre(createBook.getGenre());
        existingBook.setAuthor(createBook.getAuthor());
        existingBook.setAvailable(createBook.isAvailable());

        bookRepository.save(existingBook);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}
