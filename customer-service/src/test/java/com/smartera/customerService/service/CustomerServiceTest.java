package com.smartera.customerService.service;

import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.CustomerForTest;
import com.smartera.customerservice.repository.CustomerRepoForTestClass;
import com.smartera.customerservice.repository.CustomerRepository;
import com.smartera.customerservice.service.CustomerService;
import com.smartera.customerservice.service.CustomerServiceForTest;
import com.smartera.customerservice.service.InformationService;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * @author yilmazsahin
 * @since 3/11/2024
 */

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private CustomerRepoForTestClass customerRepoForTestClass;
    @Mock
    private InformationService informationService;
    @InjectMocks
    private CustomerService customerService;
    @InjectMocks
    private CustomerServiceForTest customerServiceForTest;


    @Before
    public void setUp() throws Exception {
        customerServiceForTest = new CustomerServiceForTest();
        customerRepoForTestClass = Mockito.mock(CustomerRepoForTestClass.class);
        informationService = Mockito.mock(InformationService.class);
        customerService.setCustomerRepository(customerRepository);
        customerServiceForTest.setCustomerRepoForTestClass(customerRepoForTestClass);
        customerServiceForTest.setInformationService(informationService);

    }

    @Test
    public void test_create_customer() {
        CustomerForTest customer = new CustomerForTest();
        customerServiceForTest.createCustomer(customer);

        //  We need to check !!!!
        System.out.println("**************************************");
        Mockito.verify(informationService).sendEmailToNewCustomer(customer);
        Mockito.verify(customerRepoForTestClass).save(customer);

        System.out.println("Customer has been saved successfully: " + customer);
        System.out.println("**************************************");
    }

    @Test
    public void testCreateCustomer() {
        //Given
        Customer customer = new Customer();
        customer.setId(1L);
        customer.setFirstName("Yılmaz");
        customer.setLastName("Sahin");
        customer.setEmail("yilmazsahinogullari@smartera.com");
        customer.setAuthorizationLevel("ADMIN");
        customer.setOrderAuthority(true);
        customer.setPassword("12345");
        customer.setPhoneNumber("05055555555");

        //When
        when(customerRepository.save(customer)).thenReturn(customer);
        Customer savedCustomer = customerService.saveCustomer(customer);


        //Then
        assertEquals(customer, savedCustomer);
        verify(customerRepository, times(1)).save(customer);
        System.out.println("Test completed successfully, Customer created: " + customer);

    }

    @Test
    public void test_check_customer_order_authority_customer_not_found() {

        //Given
        Long customerId = 99L;

        //When
        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());


        //Then
        assertFalse(customerService.checkOrderAuthority(customerId));
        System.out.println("there is no customer with this ID");
    }

    @Test
    public void test_check_customer_order_authority_with_customer_order_authority() {

        //Given

        long customerId = 1L;
        Customer customer = new Customer();
        customer.setOrderAuthority(true);

        //When
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        //Then
        assertTrue(customerService.checkOrderAuthority(customerId));
        System.out.println("Customer has authority for giving order.");
    }


    @Test
    public void test_delete_customer_by_customerId() {
        //Given
        Long customerId = 99L;

        //When
        when(customerRepository.existsById(customerId)).thenReturn(true);

        //then
        assertTrue(customerService.deleteCustomer(customerId));
        verify(customerRepository, times(1)).deleteById(customerId);
        System.out.println("Customer deleted successfully  :" + customerId);


    }
}
