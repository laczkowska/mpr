package org.example.controller;

import org.example.dto.OrderItemDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.example.service.OrderService;
import org.example.dto.OrderDTO;


import java.util.Arrays;
import java.util.List;

@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Test
    public void getOrdersByCustomer_Success() throws Exception {
        List<OrderItemDTO> items1 = List.of(
                new OrderItemDTO(1L, 2, 29.99)  // Assuming price per book
        );
        List<OrderItemDTO> items2 = List.of(
                new OrderItemDTO(2L, 1, 24.99)  // Assuming price per book
        );

        List<OrderDTO> mockOrders = List.of(
                new OrderDTO(1L, "John Doe", "123 Maple Street", items1),
                new OrderDTO(2L, "Jane Smith", "456 Oak Lane", items2)
        );
        Mockito.when(orderService.getOrdersByCustomer("John Doe")).thenReturn(mockOrders);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/customer/John Doe")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].customerName", is("John Doe")))
                .andExpect(jsonPath("$[0].items[0].bookId", is(1)))
                .andExpect(jsonPath("$[1].customerName", is("Jane Smith")))
                .andExpect(jsonPath("$[1].items[0].bookId", is(2)));
    }
}

