import com.example.library.data.Book;
import com.example.library.resource.CreateBook;
import com.example.library.resource.BookDto;
import com.example.library.service.LibraryService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library/books")
public class LibraryController {

    private final LibraryService libraryService;

    public LibraryController(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createBook(@RequestBody CreateBook createBook) {
        libraryService.createBook(createBook);
    }

    @GetMapping("/{id}")
    public BookDto getBookById(@PathVariable Long id) {
        return libraryService.getBookById(id);
    }

    @GetMapping
    public List<BookDto> getAllBooks() {
        return libraryService.getAllBooks();
    }

    @PutMapping("/{id}")
    public void updateBook(@PathVariable Long id, @RequestBody CreateBook createBook) {
        libraryService.updateBook(id, createBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable Long id) {
        libraryService.deleteBook(id);
    }
}
