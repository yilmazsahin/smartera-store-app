package com.smartera.orderservice.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 2/27/2024
 */

@Service
public class CustomerApi {

    @Autowired
    private RestTemplate restTemplate;

    private final String CUSTOMER_URL = "http://localhost:8080/api/customers";

    public boolean customerHasAuthority(Long customerId) {
        try {
            String url = CUSTOMER_URL + "/has-order-authority/" + customerId;
            ApiResponse<Boolean> response = restTemplate.getForObject(url, ApiResponse.class);
            return Optional.ofNullable(response)
                    .map(ApiResponse::getBody)
                    .orElse(true);
        } catch (Exception e) {
            System.err.println(e);
            return false;
        }
    }
}
