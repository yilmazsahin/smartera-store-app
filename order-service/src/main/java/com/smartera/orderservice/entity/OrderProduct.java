package com.smartera.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * @author yilmazsahin
 * @since 2/29/2024
 */

@Table(name = "order_product")
@Entity
@Data
public class OrderProduct {


    @EmbeddedId
    private OrderProductId id;
    @Column(name = "size")
    private int size;
}
