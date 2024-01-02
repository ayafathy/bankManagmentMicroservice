package com.bank.customermanagement.controller;

import com.bank.customermanagement.controller.CustomerController;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.facade.CustomerFacade;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.model.GeneralResponse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class CustomerControllerTest {

    @Mock
    private CustomerFacade customerFacade;

    @InjectMocks
    private CustomerController customerController;

    @Test
    void testCreateCustomer_Successful() {
        // Arrange
        CustomerRegistrationRequest request = createTestRequest();
        doNothing().when(customerFacade).createCustomer(request);

        // Act
        ResponseEntity<GeneralResponse> responseEntity = customerController.createCustomer(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().getCode().equals(200));
    }

    @Test
    void testCreateCustomer_BusinessError() throws BusinessExceptions {
        // Arrange
        CustomerRegistrationRequest request = createTestRequest();
        doThrow(new BusinessExceptions("customer email already Exist")).when(customerFacade).createCustomer(request);

        // Act
        ResponseEntity<GeneralResponse> responseEntity = customerController.createCustomer(request);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertTrue(responseEntity.getBody().getCode().equals(1));
        assertTrue(responseEntity.getBody().getMessage().contains("customer email already Exist"));
    }

    private CustomerRegistrationRequest createTestRequest() {
        return new CustomerRegistrationRequest(1L, "John", "Doe", "123 Main St", 1, "john.doe@example.com", 2);
    }
}
