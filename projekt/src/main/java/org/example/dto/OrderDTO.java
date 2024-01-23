package org.example.dto;

import org.example.data.Order;

public class OrderDTO {
    private Long id;
    private int quantity;
    private double totalPrice;
    private String customerName;
    private String shippingAddress;

    public OrderDTO(Order order) {
        this.id = order.getId();
        this.quantity = order.getQuantity();
        this.totalPrice = order.getTotalPrice();
        this.customerName = order.getCustomerName();
        this.shippingAddress = order.getShippingAddress();
    }

    // Getters
    public Long getId() {
        return id;
    }
    public int getQuantity() {
        return quantity;
    }
    public double getTotalPrice() {
        return totalPrice;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getShippingAddress() {
        return shippingAddress;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public void setShippingAddress(String shippingAddress) {
        this.shippingAddress = shippingAddress;
    }
    // Możesz dodać tutaj dodatkowe metody, jeśli są potrzebne
}
