package com.smartera.orderservice.repository;

import com.smartera.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author yilmazsahin
 * @since 2/19/2024
 */

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findOrdersByCustomerId(Long customerId);
}
