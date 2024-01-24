package org.example.dto;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String category;
    private double price;
    private int quantityInStock;

    // Konstruktor bezparametrowy
    public BookDTO() {
    }

    // Konstruktor z wszystkimi parametrami
    public BookDTO(Long id, String title, String author, String category, double price, int quantityInStock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.category = category;
        this.price = price;
        this.quantityInStock = quantityInStock;
    }

    // Gettery
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getCategory() {
        return category;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    // Settery
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }
}