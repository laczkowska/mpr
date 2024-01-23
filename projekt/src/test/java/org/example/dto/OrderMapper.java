package org.example.dto;

import org.example.data.Order;
import org.springframework.web.bind.annotation.Mapping;

public interface OrderMapper {
    @Mapping(target = "id", source = "order.id")
    OrderDTO orderToOrderDTO(Order order);
}