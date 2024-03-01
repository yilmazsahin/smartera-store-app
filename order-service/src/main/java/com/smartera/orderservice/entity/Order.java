package com.smartera.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author yilmazsahin
 * @since 2/19/2024
 */
@Data
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "customer_id")
    private Long customerId;

    @Column(name = "total_quantity")
    private double totalQuantity;

    @Column(name = "total_price")
    private double totalPrice;

    @Column(name = "date_created")
    @CreationTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;

    @Column(name = "last_updated")
    @UpdateTimestamp
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastUpdated;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "billing_address")
    private String billingAddress;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderProduct> orderProductList = new ArrayList<>();

}
