package com.smartera.orderservice.repository;

import com.smartera.orderservice.entity.OrderProduct;
import com.smartera.orderservice.entity.OrderProductId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author yilmazsahin
 * @since 2/29/2024
 */

@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct, OrderProductId> {
}
