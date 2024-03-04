package com.smartera.orderservice.controller;

import com.smartera.orderservice.api.ApiResponse;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.entity.OrderDTO;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.mapper.Mapper;
import com.smartera.orderservice.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 2/19/2024
 */
@RestController
@RequestMapping("api/orders")
public class OrderController {
    private final OrderService orderService;
    private RestTemplate restTemplate;

    public OrderController(OrderService orderService, RestTemplate restTemplate) {
        this.orderService = orderService;
        this.restTemplate = restTemplate;


    }

    @GetMapping()
    public ResponseEntity<List<OrderDTO>> getAllOrders() {
        List<Order> orders = orderService.getAllOrders();
        var dtoList = orders.stream().map(Mapper::toOrderDTO).toList();
        return ResponseEntity.ok(dtoList);
    }

    @PostMapping()
    public ResponseEntity<OrderDTO> createOrder(@RequestBody Order order) throws Exception {
        Order createdOrder = orderService.saveOrder(order);
        var dto = Mapper.toOrderDTO(createdOrder);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) throws Exception {
        Order order = orderService.getOrderById(id);

        if (order != null) {
            return order;
        } else {
            throw new Exception("Order not found with id: " + id);
        }
    }


    @GetMapping("/getOrdersByCustomerId/{id}")
    public Optional<List<Order>> getOrdersByCustomerId(Long id) {
        return orderService.getOrdersByCustomerId(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> updateOrderById(@RequestBody Order order) {

        Order updatedOrder = orderService.updateOrder(order);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteOrderById(@PathVariable Long id) {
        boolean deletedOrder = orderService.deleteOrderById(id);
        if (deletedOrder) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{orderId}/products")
    public ResponseEntity<List<Product>> getProductsByOrderId(@PathVariable Long orderId) {
        List<Product> products = orderService.getProductsByOrderId(orderId);
        if (products.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @PutMapping("/{orderId}/{productId}")
    public ResponseEntity<ApiResponse<Void>> addProductToOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        orderService.addProductToOrder(orderId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{orderId}/{productId}")
    public ResponseEntity<ApiResponse<Void>> removeProductFromOrder(@PathVariable Long productId, @PathVariable Long orderId) {
        orderService.removeProductFromOrder(orderId, productId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
