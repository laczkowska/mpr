package org.example.service;

import org.example.data.Order;
import org.example.data.Book;
import org.example.dto.OrderDTO;
import org.example.dto.OrderItemDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private BookRepository bookRepository;

    public List<OrderDTO> getOrdersByCustomer(String customerName) {
        List<Order> orders = orderRepository.findByCustomerName(customerName);
        return orders.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void placeOrder(OrderDTO orderDTO) {
        double totalPrice = 0;
        for (OrderItemDTO item : orderDTO.getItems()) {
            Book book = bookRepository.findById(item.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + item.getBookId()));
            totalPrice += book.getPrice() * item.getQuantity();

            // Create and save an order for each item
            Order order = new Order();
            order.setCustomerName(orderDTO.getCustomerName());
            order.setShippingAddress(orderDTO.getShippingAddress());
            order.setTotalPrice(book.getPrice() * item.getQuantity());
            order.setBook(book); // Link the book to the order
            order.setQuantity(item.getQuantity());
            orderRepository.save(order);
        }
    }

    private OrderDTO convertToDTO(Order order) {
        OrderItemDTO item = new OrderItemDTO(order.getBook().getId(), order.getQuantity(), order.getBook().getPrice());
        return new OrderDTO(order.getId(), order.getCustomerName(), order.getShippingAddress(), Arrays.asList(item));
    }
}

