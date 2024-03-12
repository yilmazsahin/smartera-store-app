package com.smartera.orderservice.service;

import com.smartera.orderservice.api.CustomerApi;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.entity.OrderProduct;
import com.smartera.orderservice.entity.OrderProductId;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.repository.OrderProductRepository;
import com.smartera.orderservice.repository.OrderRepository;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {
    @Mock
    private OrderProductRepository orderProductRepository;
    @Mock
    private CustomerApi customerApi;
    @Mock
    private OrderRepository orderRepository;
    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderService orderService;

    @Before
    public void setUp() throws Exception {
        orderService.setOrderRepository(orderRepository);
    }

    @Test
    public void testSaveOrder_when_customer_has_order_authority() throws Exception {

        //Given
        Order mockOrder = new Order();
        mockOrder.setCustomerId(1L);

        when(customerApi.customerHasAuthority(mockOrder.getCustomerId())).thenReturn(true);
        when(orderRepository.save(mockOrder)).thenReturn(mockOrder);


        //When
        Order result = orderService.saveOrder(mockOrder);

        // Then
        assertNotNull(result.getDateCreated());
        verify(orderRepository, times(1)).save(mockOrder);
        System.out.println("Order has been saved successfully" + mockOrder);

    }

    @Test
    void testGetOrderById_when_exists_order() {
        //Given
        Long orderId = 5L;
        Order mockOrder = new Order();
        mockOrder.setId(orderId);
        when(orderRepository.findById(any())).thenReturn(Optional.of(mockOrder));

        //When
        Order result = orderService.getOrderById(orderId);

        //Then
        assertNotNull(result);
        assertEquals(mockOrder, result);
    }

    @Test
    public void testGetProductsByOrderId_when_order_exists() {
        //GİVEN
        Long mockOrderId = 1L;
        Order mockOrder = new Order();
        mockOrder.setId(mockOrderId);

        Product mockProduct1 = new Product();
        mockProduct1.setId(1L);
        Product mockProduct2 = new Product();
        mockProduct2.setId(2L);
        Product mockProduct3 = new Product();
        mockProduct3.setId(3L);

        OrderProduct orderProduct1 = new OrderProduct();
        orderProduct1.setProduct(mockProduct1);
        OrderProduct orderProduct2 = new OrderProduct();
        orderProduct2.setProduct(mockProduct2);
        OrderProduct orderProduct3 = new OrderProduct();
        orderProduct3.setProduct(mockProduct3);

        mockOrder.setOrderProductList(List.of(orderProduct1, orderProduct2, orderProduct3));
        when(orderRepository.findById(mockOrderId)).thenReturn(Optional.of(mockOrder));

        // WHEN
        List<Product> mockProducts = orderService.getProductsByOrderId(mockOrderId);


        //THEN

        assertEquals(3, mockProducts.size());
        assertTrue(mockProducts.contains(mockProduct1));
        assertTrue(mockProducts.contains(mockProduct2));
        assertTrue(mockProducts.contains(mockProduct3));
    }

    @Test
    public void testGetProductsByOrderId_when_order_does_not_exist() {

        //GİVEN
        Long mockOrderId = 1L;
        when(orderRepository.findById(any())).thenReturn(Optional.empty());

        //WHEN
        List<Product> products = orderService.getProductsByOrderId(mockOrderId);

        //Then
        assertTrue(products.isEmpty());
    }

    @Test
    public void testAddProductToOrder_when_order_and_product_exist() {

        //GİVEN
        Long orderId = 1L;
        Long productId = 1L;

        Order mockOrder = new Order();
        mockOrder.setId(orderId);

        Product mockProduct = new Product();
        mockProduct.setId(productId);

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(mockOrder));
        when(productService.getProductById(productId)).thenReturn(mockProduct);


        // WHEN
        orderService.addProductToOrder(orderId, productId);


        // THEN
        verify(orderRepository, times(1)).findById(orderId);
        verify(productService, times(1)).getProductById(productId);
        verify(orderProductRepository, times(1)).save(any(OrderProduct.class));

    }

    @Test
    void testDeleteOrderById_return_true_when_order_exist_and_order_delete_after() {

        // given
        Long mockOrderId = 5L;
        when(orderRepository.existsById(anyLong())).thenReturn(true);

        //WHEN
        boolean result = orderService.deleteOrderById(mockOrderId);

        // THEN
        assertTrue(result);
        verify(orderRepository, times(1)).deleteById(mockOrderId);
        assertTrue(orderRepository.existsById(mockOrderId));
        System.out.println("Order deleted");

    }

    @Test
    public void testRemoveProductFromOrder_when_order_product_exists_and_size_is_one() {
        Long orderId = 1L;
        Long productId = 1L;


        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setSize(1);

        when(orderProductRepository.findById(any(OrderProductId.class))).thenReturn(Optional.of(orderProduct));

        //When
        orderService.removeProductFromOrder(orderId, productId);


        //THEN
        verify(orderProductRepository, times(1)).findById(any(OrderProductId.class));
        verify(orderProductRepository, times(1)).deleteById(any(OrderProductId.class));
    }


    @Test
    public void testRemoveProductFromOrder_when_order_product_exists_and_size_is_greater_than_one() {
        Long orderId = 1L;
        Long productId = 1L;


        OrderProduct orderProduct = new OrderProduct();
        orderProduct.setSize(2);

        when(orderProductRepository.findById(any(OrderProductId.class))).thenReturn(Optional.of(orderProduct));

        orderService.removeProductFromOrder(orderId, productId);
        //Then
        verify(orderProductRepository, times(1)).findById(any(OrderProductId.class));
        verify(orderProductRepository, times(1)).save(orderProduct);


    }

    @Test
    public void testRemoveProductFromOrder_when_order_product_does_not_exist() {
        // GİVEN
        Long orderId = 1L;
        Long productId = 1L;
//        OrderProductId orderProductId = OrderProductId.of(orderId, productId);
        when(orderProductRepository.findById(any(OrderProductId.class))).thenReturn(Optional.empty());

        //When
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            orderService.removeProductFromOrder(orderId, productId);
        });


        //   then
        assertFalse(exception.getMessage().contains("Order Product couldn't find..."));
    }

}