package com.smartera.customerservice.service;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.CustomerForTest;

/**
 * @author yilmazsahin
 * @since 3/12/2024
 */

public class InformationService {

    public void sendEmailToNewCustomer(CustomerForTest customer) {
        System.out.println("The mail has been sent to : " + customer);

    }
}
