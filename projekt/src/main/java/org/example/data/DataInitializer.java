package org.example.data;

import org.example.repository.BookRepository;
import org.example.dto.OrderDTO;
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

    @Override
    public void run(String... args) throws Exception {
        Yaml yaml = new Yaml();
        InputStream inputStream = getClass().getResourceAsStream("/books.yml");
        List<Map<String, Object>> data = yaml.load(inputStream);

        for (Map<String, Object> record : data) {
            Book book = new Book();
            book.setTitle((String) record.get("title"));
            book.setAuthor((String) record.get("author"));
            book.setCategory((String) record.get("category"));
            book.setPrice((Double) record.get("price"));
            book.setQuantityInStock((Integer) record.get("quantityInStock"));
            bookRepository.save(book);
        }
    }
}
