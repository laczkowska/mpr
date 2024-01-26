package org.example.service;

import org.example.data.Order;
import org.example.data.Book;
import org.example.data.OrderItem;
import org.example.dto.OrderDTO;
import org.example.dto.OrderItemDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BookRepository bookRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, BookRepository bookRepository) {
        this.orderRepository = orderRepository;
        this.bookRepository = bookRepository;
    }

    public List<OrderDTO> getOrdersByCustomer(String customerName) {
        return orderRepository.findByCustomerName(customerName).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public List<OrderDTO> getAllOrders() {
        return orderRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setShippingAddress(orderDTO.getShippingAddress());

        Set<OrderItem> orderItems = orderDTO.getItems().stream().map(itemDTO -> {
            Book book = bookRepository.findById(itemDTO.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + itemDTO.getBookId()));

            OrderItem orderItem = new OrderItem();
            orderItem.setBook(book);
            orderItem.setQuantity(itemDTO.getQuantity());
            orderItem.setOrder(order); // setting the order for each item
            return orderItem;
        }).collect(Collectors.toSet());

        order.setItems(orderItems);
        order.setTotalPrice(orderItems.stream().mapToDouble(item -> item.getBook().getPrice() * item.getQuantity()).sum());

        orderRepository.save(order);
    }

    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> items = order.getItems().stream()
                .map(item -> new OrderItemDTO(item.getBook().getId(), item.getQuantity(), item.getBook().getPrice()))
                .collect(Collectors.toList());

        return new OrderDTO(order.getId(), order.getCustomerName(), order.getShippingAddress(), items);
    }

}