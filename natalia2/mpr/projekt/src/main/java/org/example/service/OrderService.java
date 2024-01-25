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

import java.util.List;
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
        orderDTO.getItems().forEach(itemDTO -> {
            Book book = bookRepository.findById(itemDTO.getBookId())
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + itemDTO.getBookId()));

            Order order = new Order();
            order.setBook(book);
            order.setCustomerName(orderDTO.getCustomerName());
            order.setShippingAddress(orderDTO.getShippingAddress());
            order.setQuantity(itemDTO.getQuantity());
            order.setTotalPrice(book.getPrice() * itemDTO.getQuantity());

            orderRepository.save(order);
        });
    }

    private OrderDTO convertToDTO(Order order) {
        List<OrderItemDTO> items = List.of(new OrderItemDTO(order.getBook().getId(), order.getQuantity(), order.getBook().getPrice()));
        return new OrderDTO(order.getId(), order.getCustomerName(), order.getShippingAddress(), items);
    }
}