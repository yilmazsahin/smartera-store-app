package com.smartera.orderservice.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 * @author yilmazsahin
 * @since 2/29/2024
 */

@Table(name = "order_product")
@Entity
@Getter
@Setter
public class OrderProduct {


    @EmbeddedId
    private OrderProductId id;

    @Column(name = "size")
    private int size;

    @ManyToOne
    @MapsId("orderId")
    private Order order;

    @ManyToOne
    @MapsId("productId")
    private Product product;

}
