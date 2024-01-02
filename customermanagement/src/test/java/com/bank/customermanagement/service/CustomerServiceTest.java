package com.bank.customermanagement.service;
import com.bank.customermanagement.constant.BankConstant;
import com.bank.customermanagement.entity.Customer;
import com.bank.customermanagement.entity.lookup.CustomerStatus;
import com.bank.customermanagement.entity.lookup.CustomerType;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.repository.CustomerRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerService customerService;

    @Test
    void testRegisterCustomer_CustomerExists() throws BusinessExceptions {
        // Arrange
        CustomerRegistrationRequest request = createTestRequest();
        when(customerRepository.findByEmail(request.email())).thenReturn(Optional.of(new Customer()));

        // Act and Assert
        assertThrows(BusinessExceptions.class, () -> customerService.registerCustomer(request));
        verify(customerRepository, times(1)).findByEmail(request.email());
        verify(customerRepository, never()).save(any(Customer.class));
    }

    @Test
    void testRegisterCustomer_SaveCustomerException() throws BusinessExceptions {
        // Arrange
        CustomerRegistrationRequest request = createTestRequest();
        when(customerRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenThrow(new RuntimeException("Failed to save"));

        // Act and Assert
        assertThrows(RuntimeException.class, () -> customerService.registerCustomer(request));
        verify(customerRepository, times(1)).findByEmail(request.email());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    void testRegisterCustomer_ValidRegistration() throws BusinessExceptions {
        // Arrange
        CustomerRegistrationRequest request = createTestRequest();
        when(customerRepository.findByEmail(request.email())).thenReturn(Optional.empty());
        when(customerRepository.save(any(Customer.class))).thenReturn(createTestCustomer());

        // Act
        String associatedLegalId = customerService.registerCustomer(request);

        // Assert
        assertNotNull(associatedLegalId);
        assertEquals(BankConstant.ASSOCIATED_LEGALID_LENGTH, associatedLegalId.length());
        verify(customerRepository, times(1)).findByEmail(request.email());
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    private CustomerRegistrationRequest createTestRequest() {
        return new CustomerRegistrationRequest(
                1L,
                "John",
                "Doe",
                "123 Main St",
                1,
                "john.doe@example.com",
                2
        );
    }

    private Customer createTestCustomer() {
        return Customer.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("john.doe@example.com")
                .assciatedLegaleId("1234567")
                .customerType(new CustomerType(2))
                .customerStatus(CustomerStatus.DRAFT_STATUS)
                .build();
    }
}
