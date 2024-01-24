package org.example.dto;

import java.util.List;

public class OrderDTO {
    private Long id;
    private String customerName;
    private String shippingAddress;
    private List<OrderItemDTO> items;

    // Default constructor
    public OrderDTO() {
    }

    // Constructor with all fields
    public OrderDTO(Long id, String customerName, String shippingAddress, List<OrderItemDTO> items) {
        this.id = id;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.items = items;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    public List<OrderItemDTO> getItems() {
        return items;
    }

    public void setItems(List<OrderItemDTO> items) {
        this.items = items;
    }

    // Additional methods for convenience
    // For example, a method to calculate the total price of the order
    public double calculateTotalPrice() {
        return items.stream()
                .mapToDouble(item -> item.getPrice() * item.getQuantity())
                .sum();
    }
}


