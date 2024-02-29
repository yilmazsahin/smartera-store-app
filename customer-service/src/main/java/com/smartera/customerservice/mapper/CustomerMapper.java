package com.smartera.customerservice.mapper;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.CustomerDTO;

import java.util.List;

/**
 * @author yilmazsahin
 * @since 2/24/2024
 */


public class CustomerMapper {

    public static CustomerDTO toDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setEmail(customer.getEmail());
        dto.setFirstName(customer.getFirstName());
        dto.setLastName(customer.getLastName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setRole(customer.getAuthorizationLevel());
        dto.setOrderAuthority(customer.isOrderAuthority());
        return dto;

    }
}
