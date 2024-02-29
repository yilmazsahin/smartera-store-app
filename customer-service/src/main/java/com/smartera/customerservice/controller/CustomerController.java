package com.smartera.customerservice.controller;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.model.ApiResponse;
import com.smartera.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

/**
 * @author yilmazsahin
 * @since 2/13/2024
 */

@RestController
@RequestMapping("/api/customers")


public class CustomerController {
    private final CustomerService customerService;

    private RestTemplate restTemplate;

    @Autowired
    public CustomerController(CustomerService customerService,
                              RestTemplate restTemplate) {
        this.customerService = customerService;
        this.restTemplate = restTemplate;
    }

    @PostMapping()
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        Customer createCustomer = customerService.saveCustomer(customer);
        return ResponseEntity.ok(createCustomer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long id) {
        Customer customer = customerService.getCustomerById(id);

//        List orders = this.restTemplate.getForObject("http:localhost:8081/api/orders/customerId/" + id, List.class);

        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }


    @GetMapping()
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.ok(customers);
    }


    @PutMapping("")
    public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
        Customer updatedCustomer = customerService.updateCustomer(customer);
        if (updatedCustomer != null) {
            return ResponseEntity.ok(updatedCustomer);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/getCustomerByEmail/{email}")
    public ResponseEntity<Optional<Customer>> getCustomerByEmail(String email) {
        Optional<Customer> customer = customerService.getCustomerByEmail(email);
        if (customer != null) {
            return ResponseEntity.ok(customer);
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> deleteCustomer(@PathVariable Long id) {
        boolean deleted = customerService.deleteCustomer(id);
        if (deleted) {
            return ApiResponse.of(HttpStatus.OK);
        } else {
            return ApiResponse.of(HttpStatus.BAD_REQUEST, "couldn't be deleted");
        }
    }

    @GetMapping("/has-order-authority/{customerId}")
    public ApiResponse<Boolean> hasOrderAuthority(@PathVariable Long customerId) {
        boolean orderAuthority = customerService.checkOrderAuthority(customerId);
        return ApiResponse.of(HttpStatus.OK, orderAuthority);
    }

}
