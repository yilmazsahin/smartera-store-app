package com.smartera.customerservice.entity;


import lombok.Data;

/**
 * @author yilmazsahin
 * @since 2/20/2024
 */
@Data
public class GetCustomerDetail {
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private Order orders;
    private String authorizationLevel;
    private String password;

}
