package com.smartera.orderservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author yilmazsahin
 * @since 3/2/2024
 */

@Getter
@Setter
public class OrderDTO implements Serializable {

    private Long id;
    private Long customerId;
    private double totalQuantity;
    private double totalPrice;
    private String shippingAddress;
    private String billingAddress;
    private List<ProductDTO> products;
}
