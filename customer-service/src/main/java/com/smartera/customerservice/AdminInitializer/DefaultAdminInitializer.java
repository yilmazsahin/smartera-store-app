package com.smartera.customerservice.AdminInitializer;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author yilmazsahin
 * @since 3/4/2024
 */
@Component
public class DefaultAdminInitializer implements CommandLineRunner {
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public void run(String... args) throws Exception {
        if (customerRepository.findByEmail("administrator@mail.com").isEmpty()) {
            Customer admin = new Customer();
            admin.setEmail("administrator@mail.com");
            admin.setFirstName("Admin");
            admin.setLastName("Administrator");
            admin.setPhoneNumber("05555555555");
            admin.setAuthorizationLevel("ADMIN");
            admin.setPassword("12345");
            admin.setOrderAuthority(true);
            customerRepository.save(admin);
        }

    }
}
