package com.smartera.customerservice.entity;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * @author yilmazsahin
 * @since 2/24/2024
 */

@Getter
@Setter
public class CustomerDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private String role;
    private boolean orderAuthority;


}
