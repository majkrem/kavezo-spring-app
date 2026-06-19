package hu.reich.kavezo.service;

import hu.reich.kavezo.model.Order;
import hu.reich.kavezo.model.Product;
import hu.reich.kavezo.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    private Product espresso;
    private Product croissant;

    @BeforeEach
    void setUp() {
        espresso = new Product("Espresso", "Erős kávé", 890, "Kávé");
        croissant = new Product("Croissant", "Friss croissant", 690, "Sütemény");
    }

    @Test
    void saveOrder_shouldCalculateTotalPrice() {
        List<Product> products = List.of(espresso, croissant);

        when(orderRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        var order = orderService.saveOrder("Teszt Elek", products);

        assertEquals("Teszt Elek", order.getCustomerName());
        assertEquals(1580.0, order.getTotalPrice(), 0.01);
        assertEquals(2, order.getProducts().size());
        verify(orderRepository, times(1)).save(any());
    }

    @Test
    void findAll_shouldReturnAllOrders() {
        // Arrange (előkészítés)
        List<Order> orders = List.of(
                new Order(),
                new Order()
        );
        when(orderRepository.findAll()).thenReturn(orders);

        // Act (végrehajtás)
        List<Order> result = orderService.findAll();

        // Assert (ellenőrzés)
        assertEquals(2, result.size());
        verify(orderRepository, times(1)).findAll();
    }
}