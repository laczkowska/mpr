package org.example.repository;

import org.example.data.Order;
import org.example.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class OrderRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private OrderRepository orderRepository;

    // Ustawienie danych testowych
    @BeforeEach
    public void setUp() {
        // Tutaj możesz wstawić przykładowe zamówienia do bazy danych
        Order order1 = new Order();
        order1.setCustomerName("John Doe");
        // Ustaw pozostałe właściwości order1

        Order order2 = new Order();
        order2.setCustomerName("Jane Doe");
        // Ustaw pozostałe właściwości order2

        entityManager.persist(order1);
        entityManager.persist(order2);
        entityManager.flush();
    }

    @Test
    public void whenFindByCustomerName_thenReturnOrders() {
        // Testowanie metody findByCustomerName
        String customerName = "John Doe";
        List<Order> foundOrders = orderRepository.findByCustomerName(customerName);

        // Asercje
        assertFalse(foundOrders.isEmpty(), "Lista zamówień nie powinna być pusta");
        assertEquals(foundOrders.get(0).getCustomerName(), customerName, "Nazwa klienta powinna być zgodna z oczekiwaną");
    }

    @Test
    public void whenFindByCustomerNameAndNoOrders_thenReturnEmptyList() {
        // Testowanie metody findByCustomerName dla nieistniejącego klienta
        String customerName = "Nonexistent Customer";
        List<Order> foundOrders = orderRepository.findByCustomerName(customerName);

        // Asercje
        assertTrue(foundOrders.isEmpty(), "Lista zamówień powinna być pusta dla nieistniejącego klienta");
    }
}