package com.smartera.orderservice.mapper;

import com.smartera.orderservice.entity.*;

import java.util.ArrayList;
import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 3/2/2024
 */

public class Mapper {

    public static OrderDTO toOrderDTO(Order order) {
        OrderDTO dto = new OrderDTO();
        dto.setId(order.getId());
        dto.setCustomerId(order.getCustomerId());
        dto.setBillingAddress(order.getBillingAddress());
        dto.setShippingAddress(order.getShippingAddress());
        var productList = Optional.ofNullable(order.getOrderProductList())
                .orElse(new ArrayList<>())
                .stream()
                .map(Mapper::toProductDTO)
                .toList();
        dto.setProducts(productList);
        return dto;
    }

    public static ProductDTO toProductDTO(OrderProduct orderProduct) {
        ProductDTO dto = new ProductDTO();
        dto.setId(orderProduct.getProduct().getId());
        dto.setName(orderProduct.getProduct().getName());
        dto.setPrice(orderProduct.getProduct().getPrice());
        dto.setSize(orderProduct.getSize());
        return dto;
    }

    public static ProductDTO toProductDTO(Product product) {
        ProductDTO dto = new ProductDTO();
        dto.setId(product.getId());
        dto.setName(product.getName());
        dto.setPrice(product.getPrice());
        return dto;
    }
}
