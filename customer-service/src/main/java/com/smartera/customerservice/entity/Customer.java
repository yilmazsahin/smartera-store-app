package com.smartera.customerservice.entity;

import com.smartera.customerservice.repository.CustomerRepository;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author yilmazsahin
 * @since 2/13/2024
 */
@Data
@Entity
@Table(name = "customers")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "email")
    private String email;

    @Column(name = "orders")
    private Long orderId;


    @Column(name = "authorization_level")
    private String authorizationLevel;

    @Column(name = "password")
    private String password;


    @Column(name = "order_authority", columnDefinition = "boolean DEFAULT true")
    private boolean orderAuthority;
}
