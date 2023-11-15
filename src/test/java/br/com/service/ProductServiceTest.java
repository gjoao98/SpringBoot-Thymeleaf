package br.com.service;

import br.com.fiap.model.Product;
import br.com.fiap.repository.ProductRepository;
import br.com.fiap.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    private Product product;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        LocalDate localDate = LocalDate.parse("2023-11-14");

        product = new Product();
        product.setId(1L);
        product.setName("Teste de Jogo");
        product.setDescription("Teste Developer");
        product.setRegisterDate(localDate);
        product.setPrice(129.99);
    }

    @Test
    void findAll_ShouldReturnListOfProducts() {
        when(productRepository.findAll()).thenReturn(Arrays.asList(product));

        List<Product> result = productService.findAll();

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        assertEquals(product, result.get(0));
    }

    @Test
    void findById_ShouldReturnProduct() {
        when(productRepository.findById(1L)).thenReturn(Optional.of(product));

        Optional<Product> result = productService.findById(1L);

        assertTrue(result.isPresent());
        assertEquals(product, result.get());
    }

    @Test
    void save_ShouldReturnSavedProduct() {
        when(productRepository.save(any(Product.class))).thenReturn(product);

        Product savedProduct = productService.save(product);

        assertNotNull(savedProduct);
        assertEquals(product, savedProduct);
    }

    @Test
    void deleteById_ShouldDeleteProductById() {
        doNothing().when(productRepository).deleteById(1L);

        productRepository.deleteById(1L);

        verify(productRepository, times(1)).deleteById(1L);
    }
}
