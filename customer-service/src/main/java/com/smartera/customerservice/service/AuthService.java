package com.smartera.customerservice.service;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 2/15/2024
 */
@Service
public class AuthService {
    @Autowired
    private CustomerRepository customerRepository;

    public Customer authenticateUser(String email, String password) {
        Optional<Customer> customerOptional = customerRepository.findByEmail(email);

        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            if (customer.getPassword().equals(password)) {
                System.out.println("Password is true : " + password);
                return customer;
            } else {
                System.out.println("Password wrong : " + password);

            }
        }
        return null;
    }
}
