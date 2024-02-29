package com.smartera.orderservice.repository;

import com.smartera.orderservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author yilmazsahin
 * @since 2/28/2024
 */

public interface ProductRepository extends JpaRepository<Product, Long> {
}
