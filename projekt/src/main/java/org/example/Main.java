package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // Adnotacja wskazująca, że jest to aplikacja Spring Boot
public class Main {
    public static void main(String[] args) {
        // Uruchamia kontekst aplikacji Spring Boot
        SpringApplication.run(Main.class, args);
    }
}
