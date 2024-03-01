package com.smartera.orderservice.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author yilmazsahin
 * @since 2/20/2024
 */
@Data
public class GetOrderDetail {

    private Long id;

    private Customer customerId;

    private double totalQuantity;

    private double totalPrice;

    private Date dateCreated;

    private Date lastUpdated;

    private String shippingAddress;

    private String billingAddress;

}
