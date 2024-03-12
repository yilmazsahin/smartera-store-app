package com.smartera.customerservice.repository;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.CustomerForTest;

/**
 * @author yilmazsahin
 * @since 3/12/2024
 */

public class CustomerRepoForTestClass {
    public void save(CustomerForTest customer) {
        System.out.println("Customer has been saved successfully" + customer);
    }
}
