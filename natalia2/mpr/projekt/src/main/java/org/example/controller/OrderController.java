package org.example.controller;

import org.example.dto.OrderDTO;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<OrderDTO> orders = orderService.getAllOrders();
        model.addAttribute("orders", orders);
        return "orders";
    }

    @GetMapping("/customer/{customerName}")
    public ResponseEntity<List<OrderDTO>> getOrdersByCustomer(@PathVariable String customerName) {
        List<OrderDTO> orders = orderService.getOrdersByCustomer(customerName);
        return ResponseEntity.ok(orders);
    }

    @PostMapping
    public ResponseEntity<String> placeOrder(@RequestBody OrderDTO orderDTO) {
        orderService.placeOrder(orderDTO);
        return ResponseEntity.ok("Zamówienie zostało złożone.");
    }

    @GetMapping({"/{orderId}/details", "/{orderId}"})
    public ResponseEntity<OrderDTO> getOrderDetails(@PathVariable Long orderId) {
        OrderDTO order = orderService.getOrderDetails(orderId);
        return ResponseEntity.ok(order);
    }
}
