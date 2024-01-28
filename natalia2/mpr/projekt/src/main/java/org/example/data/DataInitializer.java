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
            Order order = new Order();
            order.setCustomerName((String) record.get("customerName"));
            order.setShippingAddress((String) record.get("shippingAddress"));

            List<Map<String, Object>> itemsData = (List<Map<String, Object>>) record.get("items");
            for (Map<String, Object> itemRecord : itemsData) {
                OrderItem item = new OrderItem();
                Long bookId = ((Number) itemRecord.get("bookId")).longValue();
                bookRepository.findById(bookId).ifPresentOrElse(book -> {
                    item.setBook(book);
                    item.setQuantity((Integer) itemRecord.get("quantity"));
                    item.setOrder(order);
                    order.getItems().add(item);
                }, () -> {
                    System.out.println("Book not found with ID: " + bookId);
                });
            }
            orderRepository.save(order);
        }
    }

}
