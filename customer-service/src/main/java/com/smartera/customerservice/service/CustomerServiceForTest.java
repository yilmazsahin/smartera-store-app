package com.smartera.customerservice.service;

import com.smartera.customerservice.entity.CustomerForTest;
import com.smartera.customerservice.repository.CustomerRepoForTestClass;

/**
 * @author yilmazsahin
 * @since 3/12/2024
 */

public class CustomerServiceForTest {
    private CustomerRepoForTestClass customerRepoForTestClass;
    private InformationService informationService;

    public void createCustomer(CustomerForTest customer) {
        customerRepoForTestClass.save(customer);
        informationService.sendEmailToNewCustomer(customer);
    }

    public void setCustomerRepoForTestClass(CustomerRepoForTestClass customerRepoForTestClass) {
        this.customerRepoForTestClass = customerRepoForTestClass;
    }

    public void setInformationService(InformationService informationService) {
        this.informationService = informationService;
    }
}
