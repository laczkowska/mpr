package org.example.service;

import org.example.data.Order;
import org.example.dto.OrderDTO;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public List<OrderDTO> getOrdersByCustomer(String customerName) {
        List<Order> orders = orderRepository.findByCustomerName(customerName);
        List<OrderDTO> collect = orders.stream().map(OrderDTO::new).collect(Collectors.toList());
        return collect;
    }


    public void placeOrder(OrderDTO orderDTO) {
        Order order = new Order();
        order.setQuantity(orderDTO.getQuantity());
        order.setTotalPrice(orderDTO.getTotalPrice());
        order.setCustomerName(orderDTO.getCustomerName());
        order.setShippingAddress(orderDTO.getShippingAddress());
        orderRepository.save(order);
    }
}
