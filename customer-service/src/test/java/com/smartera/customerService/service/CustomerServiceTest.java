package com.smartera.customerService.service;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.repository.CustomerRepository;
import com.smartera.customerservice.service.CustomerService;
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

    @InjectMocks
    private CustomerService customerService;



    @Before
    public void setUp() throws Exception {

        customerService.setCustomerRepository(customerRepository);

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
        when(customerRepository.save(any())).thenReturn(customer);
        //When

        Customer savedCustomer = customerService.saveCustomer(customer);


        //Then
        assertEquals(customer, savedCustomer);
        verify(customerRepository, times(1)).save(customer);
        System.out.println("Test completed successfully, Customer created: " + customer);

    }

    @Test
    public void testDeleteCustomer_return_true_when_exists_customer_and_customer_delete_after() {

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
