package com.bank.customermanagement.repository;

import com.bank.customermanagement.entity.Customer;
import com.bank.customermanagement.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerRepositoryTest {

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void testFindByEmail_CustomerExists() {
        // Arrange
        String email = "john.doe@example.com";
        Customer expectedCustomer = createTestCustomer();
        when(customerRepository.findByEmail(email)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Optional<Customer> result = customerRepository.findByEmail(email);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedCustomer, result.get());
        verify(customerRepository, times(1)).findByEmail(email);
    }

    @Test
    void testFindByEmail_CustomerDoesNotExist() {
        // Arrange
        String email = "nonexistent@example.com";
        when(customerRepository.findByEmail(email)).thenReturn(Optional.empty());

        // Act
        Optional<Customer> result = customerRepository.findByEmail(email);

        // Assert
        assertFalse(result.isPresent());
        verify(customerRepository, times(1)).findByEmail(email);
    }

    @Test
    void testFindByAssociatedLegalId_CustomerExists() {
        // Arrange
        String associatedLegalId = "1234567";
        Customer expectedCustomer = createTestCustomer();
        when(customerRepository.findByAssciatedLegaleId(associatedLegalId)).thenReturn(Optional.of(expectedCustomer));

        // Act
        Optional<Customer> result = customerRepository.findByAssciatedLegaleId(associatedLegalId);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(expectedCustomer, result.get());
        verify(customerRepository, times(1)).findByAssciatedLegaleId(associatedLegalId);
    }

    @Test
    void testFindByAssociatedLegalId_CustomerDoesNotExist() {
        // Arrange
        String associatedLegalId = "nonexistent";
        when(customerRepository.findByAssciatedLegaleId(associatedLegalId)).thenReturn(Optional.empty());

        // Act
        Optional<Customer> result = customerRepository.findByAssciatedLegaleId(associatedLegalId);

        // Assert
        assertFalse(result.isPresent());
        verify(customerRepository, times(1)).findByAssciatedLegaleId(associatedLegalId);
    }

    private Customer createTestCustomer() {
        return Customer.builder()
                .id(1)
                .firstName("John")
                .lastName("Doe")
                .address("123 Main St")
                .email("john.doe@example.com")
                .assciatedLegaleId("1234567")
                .build();
    }
}
