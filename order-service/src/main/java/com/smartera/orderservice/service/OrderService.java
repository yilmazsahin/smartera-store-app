package com.smartera.orderservice.service;

import com.smartera.orderservice.api.CustomerApi;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.entity.OrderProduct;
import com.smartera.orderservice.entity.OrderProductId;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.repository.OrderProductRepository;
import com.smartera.orderservice.repository.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yilmazsahin
 * @since 2/19/2024
 */
@Service
public class OrderService {
    private OrderRepository orderRepository;
    private final CustomerApi customerApi;
    private final OrderProductRepository orderProductRepository;


    public OrderService(OrderRepository orderRepository, CustomerApi customerApi, OrderProductRepository orderProductRepository) {
        this.orderRepository = orderRepository;
        this.customerApi = customerApi;
        this.orderProductRepository = orderProductRepository;
    }


    public Optional<List<Order>> getOrdersByCustomerId(Long customerId) {
        List<Order> orders = orderRepository.findOrdersByCustomerId(customerId);
        return Optional.ofNullable(orders);
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public Order saveOrder(Order order) throws Exception {
        boolean customerHasOrderAuthority = customerApi.customerHasAuthority(order.getCustomerId());
        if (customerHasOrderAuthority) {
            order.setDateCreated(new Date());
            return orderRepository.save(order);
        }
        throw new Exception("The Customer hasn't have order authority");
    }

    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    public Order updateOrder(Order order) {
        if (order == null || order.getId() == null) {
            System.err.println("Order is Null");
            return null;
        }

        var orderOptional = orderRepository.findById(order.getId());

        if (orderOptional.isPresent()) {
            var existsOrder = orderOptional.get();
            existsOrder.setBillingAddress(order.getBillingAddress());
            existsOrder.setShippingAddress(order.getShippingAddress());
            existsOrder.setTotalPrice(order.getTotalPrice());
            existsOrder.setTotalQuantity(order.getTotalQuantity());
            existsOrder.setLastUpdated(new Date());
            return orderRepository.save(existsOrder);
        }
        return null;
    }

    public List<Product> getProductsByOrderId(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            return new ArrayList<>(order.getProducts());
        }
        return Collections.emptyList();
    }

    public boolean deleteOrderById(Long id) {
        if (orderRepository.existsById(id)) {
            orderRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

    @Transactional
    public void addProductToOrder(Long orderId, Long productId) {
        // todo check validations
        var orderProductId = OrderProductId.of(orderId, productId);
        var optionalOrderProduct = orderProductRepository.findById(orderProductId);
        if (optionalOrderProduct.isPresent()) {
            var orderProduct = optionalOrderProduct.get();
            orderProduct.setSize(orderProduct.getSize() + 1);
            orderProductRepository.save(orderProduct);
        } else {
            var orderProduct = new OrderProduct();
            orderProduct.setSize(1);
            orderProduct.setId(orderProductId);
            orderProductRepository.save(orderProduct);
        }
    }

    public void removeProductFromOrder(Long orderId, Long productId) {
        var orderProductId = OrderProductId.of(orderId, productId);
        var optionalOrderProduct = orderProductRepository.findById(orderProductId);
        if (optionalOrderProduct.isPresent()) {
            var orderProduct = optionalOrderProduct.get();
            if (orderProduct.getSize() == 1) {
                orderProductRepository.deleteById(orderProductId);
            } else {
                orderProduct.setSize(orderProduct.getSize() - 1);
                orderProductRepository.save(orderProduct);
            }
        } else {
            //todo throw Exception
        }
    }

}
