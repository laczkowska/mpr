package org.example.service;

import org.example.data.Book;
import org.example.data.Order;
import org.example.dto.OrderDTO;
import org.example.dto.OrderItemDTO;
import org.example.exception.ResourceNotFoundException;
import org.example.repository.BookRepository;
import org.example.repository.OrderRepository;
import org.example.service.OrderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private BookRepository bookRepository;

    @InjectMocks
    private OrderService orderService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testPlaceOrder() {
        // Arrange - Set up the test data and expectations
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setCustomerName("John");
        orderDTO.setShippingAddress("123 Main St");

        // Mock book data
        long bookId = 1L;
        Book book = new Book();
        book.setId(bookId);
        book.setPrice(25.0);

        OrderItemDTO orderItem = new OrderItemDTO(bookId, 2); // Order 2 copies of the book

        when(bookRepository.findById(bookId)).thenReturn(Optional.of(book)); // Mock bookRepository behavior

        // Act - Call the method you want to test
        orderService.placeOrder(orderDTO);

        // Assert - Check if the order is created correctly
        verify(orderRepository, times(1)).save(any(Order.class)); // Verify that save method is called once

        // You can add more assertions to verify the order creation if needed
    }
}
