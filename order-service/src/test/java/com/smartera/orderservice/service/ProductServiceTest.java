package com.smartera.orderservice.service;

import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.repository.ProductRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {
    @Mock
    private ProductRepository productRepository;
    @InjectMocks
    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService.setProductRepository(productRepository);
    }

    @Test
   public void testSaveProduct() {
        Product mockProduct = new Product();
        mockProduct.setName("Kalem");
        mockProduct.setPrice(50);
        mockProduct.setId(1L);
        final var expectedProduct = mockProduct;
        expectedProduct.setPrice(mockProduct.getPrice() * 0.2);

        when(productRepository.save(any())).thenReturn(mockProduct);

        //when

        Product result = productService.saveProduct(mockProduct);

        // then
        assertEquals(expectedProduct, result);
        verify(productRepository, times(1)).save(mockProduct);
        System.out.println("Product saved Successfully" + mockProduct);
    }

    @Test
    void testGetProductById_when_exists_product() {
//        Given
        Long id = 5L;
        Product mockProduct = new Product();
        mockProduct.setId(id);
        when(productRepository.findById(any())).thenReturn(Optional.of(mockProduct));

        //When
        Product result = productService.getProductById(id);

        //Then
        assertNotNull(result);
        assertEquals(mockProduct, result);
        System.out.println("Succeed" + result);
    }

    @Test
    public void testDeleteProduct_return_true_when_exists_product_and_product_delete_after() {

        //Given
        Long mockProductId = 5L;
        when(productRepository.existsById(anyLong())).thenReturn(true);

        //When
        boolean result = productService.deleteProduct(mockProductId);

        // Then

        assertTrue(result);
        verify(productRepository, times(1)).deleteById(mockProductId);
        assertTrue(productRepository.existsById(mockProductId));
        System.out.println("Product deleted successfully.");


    }
}