package com.smartera.orderservice.entity;

import jakarta.persistence.Column;
import lombok.Data;

/**
 * @author yilmazsahin
 * @since 2/20/2024
 */
@Data
public class Customer {
    private Long id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private String email;

    private Long orderId;

    private String authorizationLevel;

    private String password;


}
