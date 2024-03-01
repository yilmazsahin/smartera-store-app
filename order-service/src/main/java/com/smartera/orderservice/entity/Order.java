package com.smartera.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @ManyToMany
    @JoinTable(name = "order_product",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id"))
    private Set<Product> products = new HashSet<>();

}
