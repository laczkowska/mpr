package org.example.controller;

import org.example.data.Book;
import org.example.data.Order;
import org.example.data.OrderItem;
import org.example.dto.BookDTO;
import org.example.dto.OrderDTO;
import org.example.dto.OrderItemDTO;
import org.example.service.BookService;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private BookService bookService; // Service to fetch BookDTO objects

    @GetMapping("/orders")
    public String viewOrders(Model model) {
        List<OrderDTO> orderDTOs = orderService.getAllOrders();
        List<Order> orders = convertToOrders(orderDTOs);
        model.addAttribute("orders", orders);
        return "view-orders";
    }

    private List<Order> convertToOrders(List<OrderDTO> orderDTOs) {
        return orderDTOs.stream().map(this::convertToOrder).collect(Collectors.toList());
    }

    private Order convertToOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setCustomerName(orderDTO.getCustomerName());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setItems(convertToOrderItemsSet(orderDTO.getItems()));
        order.setTotalPrice(orderDTO.calculateTotalPrice());
        return order;
    }

    private Set<OrderItem> convertToOrderItemsSet(List<OrderItemDTO> orderItemDTOs) {
        return orderItemDTOs.stream()
                .map(this::convertToOrderItem)
                .collect(Collectors.toSet());
    }

    private OrderItem convertToOrderItem(OrderItemDTO orderItemDTO) {
        OrderItem orderItem = new OrderItem();
        BookDTO bookDTO = bookService.getBookById(orderItemDTO.getBookId());
        Book book = convertToBook(bookDTO);
        orderItem.setBook(book);
        orderItem.setQuantity(orderItemDTO.getQuantity());
        return orderItem;
    }

    private Book convertToBook(BookDTO bookDTO) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setAuthor(bookDTO.getAuthor());
        return book;
    }
}