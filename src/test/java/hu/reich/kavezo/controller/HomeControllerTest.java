package hu.reich.kavezo.controller;

import hu.reich.kavezo.service.OrderService;
import hu.reich.kavezo.service.ProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class HomeControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @Test
    void home_shouldReturnIndexPage() {
        String view = homeController.home(model);

        assertEquals("index", view);
        verify(model, atLeastOnce()).addAttribute(eq("products"), any());
        verify(model, atLeastOnce()).addAttribute(eq("orders"), any());
    }
}