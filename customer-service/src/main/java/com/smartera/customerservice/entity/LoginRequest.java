package com.smartera.customerservice.entity;

import lombok.Data;

/**
 * @author yilmazsahin
 * @since 2/15/2024
 */

@Data
public class LoginRequest {
    private String email;
    private String password;
    private String authorizationLevel;
    @Override
    public String toString() {
        return "LoginRequest{email='" + email + "', password='" + password + "', authorizationLevel='" + authorizationLevel + "'}";
    }
}
