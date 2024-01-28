package org.example.data;

import org.example.repository.BookRepository;
import org.example.dto.OrderDTO;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

@Component
public class DataInitializer implements CommandLineRunner {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void run(String... args) throws Exception {
        loadBooks();
        loadOrders();
    }

    private void loadBooks() throws Exception {
        Yaml yaml = new Yaml();
        InputStream inputStream = getClass().getResourceAsStream("/books.yml");
        List<Map<String, Object>> bookData = yaml.load(inputStream);

        for (Map<String, Object> record : bookData) {
            Book book = new Book();
            book.setTitle((String) record.get("title"));
            book.setAuthor((String) record.get("author"));
            book.setCategory((String) record.get("category"));
            book.setPrice((Double) record.get("price"));
            book.setQuantityInStock((Integer) record.get("quantityInStock"));
            bookRepository.save(book);
        }
    }

    private void loadOrders() throws Exception {
        Yaml yaml = new Yaml();
        InputStream inputStream = getClass().getResourceAsStream("/orders.yml");
        List<Map<String, Object>> orderData = yaml.load(inputStream);

        for (Map<String, Object> record : orderData) {
            String customerName = (String) record.get("customerName");
            String shippingAddress = (String) record.get("shippingAddress");

            List<Map<String, Object>> itemsData = (List<Map<String, Object>>) record.get("items");
            for (Map<String, Object> itemRecord : itemsData) {
                Long bookId = ((Number) itemRecord.get("bookId")).longValue();
                Integer quantity = (Integer) itemRecord.get("quantity");

                // Retrieve book details from the repository
                Book book = bookRepository.findById(bookId).orElse(null);
                if (book != null) {
                    // Display detailed book information
                    System.out.println("| Customer: " + customerName +
                            " | Address: " + shippingAddress +
                            " | Book Title: " + book.getTitle() +
                            " | Book ID: " + bookId +
                            " | Quantity: " + quantity + " |");
                } else {
                    System.out.println("Book not found for ID: " + bookId);
                }
            }
        }
    }

}