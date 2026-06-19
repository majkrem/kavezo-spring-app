package hu.reich.kavezo.service;

import hu.reich.kavezo.model.Product;
import hu.reich.kavezo.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void findAll_shouldReturnAllProducts() {
        List<Product> products = List.of(
                new Product("Espresso", "", 890, "Kávé")
        );
        when(productRepository.findAll()).thenReturn(products);

        List<Product> result = productService.findAll();

        assertEquals(1, result.size());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void deleteById_shouldCallRepositoryDelete() {
        Long id = 5L;
        doNothing().when(productRepository).deleteById(id);

        productService.deleteById(id);

        verify(productRepository, times(1)).deleteById(id);
    }

    @Test
    void save_shouldSaveAndReturnProduct() {
        Product productToSave = new Product("Latte", "Kávé tejjel", 1390, "Kávé");
        when(productRepository.save(any(Product.class))).thenReturn(productToSave);

        Product result = productService.save(productToSave);

        assertNotNull(result);
        assertEquals("Latte", result.getName());
        verify(productRepository, times(1)).save(productToSave);
    }
}