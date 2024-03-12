package com.smartera.customerservice.service;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.CustomerForTest;
import com.smartera.customerservice.entity.Order;
import com.smartera.customerservice.repository.CustomerRepoForTestClass;
import com.smartera.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 2/13/2024
 */
@Service
public class CustomerService {

    private CustomerRepository customerRepository;
    private final String orderServiceUrl = "http://localhost:8081/api/orders";
    private final RestTemplate restTemplate;


    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Autowired
    public CustomerService(CustomerRepository customerRepository, RestTemplate restTemplate) {
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
    }

    public Customer saveCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    public Customer getCustomerById(Long id) {
        return customerRepository.findById(id).orElse(null);
    }

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer updateCustomer(Customer customer) {
        if (customer == null || customer.getId() == null) {
            System.err.println("Customer is null");
            return null;
        }

        var customerOptional = customerRepository.findById(customer.getId());
        if (customerOptional.isPresent()) {
            var existsCustomer = customerOptional.get();
            existsCustomer.setFirstName(customer.getFirstName());
            existsCustomer.setLastName(customer.getLastName());
            existsCustomer.setEmail(customer.getEmail());
            existsCustomer.setPhoneNumber(customer.getPhoneNumber());
            return customerRepository.save(existsCustomer);
        }

        return null;
    }

    public boolean deleteCustomer(Long id) {

        if (customerRepository.existsById(id)) {
            customerRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public Order getOrderById(Long orderId) {
        String url = orderServiceUrl + "/" + orderId;
        return restTemplate.getForObject(url, Order.class);
    }

    public Optional<Customer> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email);
    }

    public boolean checkOrderAuthority(Long customerId) {
        return Optional.ofNullable(customerId)
                .map(this::getCustomerById)
                .map(Customer::isOrderAuthority)
                .orElse(false);
    }
}
