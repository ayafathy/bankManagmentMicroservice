package com.bank.customermanagement.facade;

import com.bank.customermanagement.client.AccountClient;
import com.bank.customermanagement.constant.BankConstant;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.model.AddAccountRequest;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.model.GeneralResponse;
import com.bank.customermanagement.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class CustomerFacadeTest {


    @Mock
    private CustomerService customerService;

    @Mock
    private AccountClient accountClient;

    @InjectMocks
    private CustomerFacade customerFacade;

    @Test
    void testCreateCustomer_Successful() throws BusinessExceptions {
        // Arrange
        CustomerRegistrationRequest registrationRequest = createTestRegistrationRequest();
        when(customerService.registerCustomer(registrationRequest)).thenReturn("associatedLegalId");

        AddAccountRequest addAccountRequest = new AddAccountRequest("associatedLegalId", registrationRequest.accountTypeId(), "john.doe@example.com");
        ResponseEntity<GeneralResponse> responseEntity = new GeneralResponse().response(200, "Success", HttpStatus.OK);
        when(accountClient.addAccount(any(AddAccountRequest.class))).thenReturn(responseEntity);

        // Act
        customerFacade.createCustomer(registrationRequest);

        // Assert
        verify(customerService, times(1)).registerCustomer(registrationRequest);
        verify(accountClient, times(1)).addAccount(addAccountRequest);
    }

    @Test
    void testCreateCustomer_FailureInAccountService() {
        // Arrange
        CustomerRegistrationRequest registrationRequest = createTestRegistrationRequest();
        when(customerService.registerCustomer(registrationRequest)).thenReturn("associatedLegalId");

        AddAccountRequest addAccountRequest = new AddAccountRequest("associatedLegalId", registrationRequest.accountTypeId(), "john.doe@example.com");
        ResponseEntity<GeneralResponse> responseEntity = new GeneralResponse().response (500, "Internal Server Error", HttpStatus.INTERNAL_SERVER_ERROR);
        when(accountClient.addAccount(any(AddAccountRequest.class))).thenReturn(responseEntity);

        // Act and Assert
        BusinessExceptions exception = assertThrows(BusinessExceptions.class, () -> customerFacade.createCustomer(registrationRequest));
        verify(customerService, times(1)).registerCustomer(registrationRequest);
        verify(accountClient, times(1)).addAccount(addAccountRequest);
        // Verify that the exception message contains the expected error message
        assertTrue(exception.getMessage().contains(BankConstant.FAILURE_IN_ACCOUNT_SERVICE));
    }

    @Test
    void testCreateCustomer_FailureInAccountResponse() {
        // Arrange
        CustomerRegistrationRequest registrationRequest = createTestRegistrationRequest();
        when(customerService.registerCustomer(registrationRequest)).thenReturn("associatedLegalId");

        AddAccountRequest addAccountRequest = new AddAccountRequest("associatedLegalId", registrationRequest.accountTypeId(), "john.doe@example.com");
        ResponseEntity<GeneralResponse> responseEntity = new GeneralResponse().response(1, "buisness error", HttpStatus.OK);
        when(accountClient.addAccount(any(AddAccountRequest.class))).thenReturn(responseEntity);

        // Act and Assert
        BusinessExceptions exception = assertThrows(BusinessExceptions.class, () -> customerFacade.createCustomer(registrationRequest));
        verify(customerService, times(1)).registerCustomer(registrationRequest);
        verify(accountClient, times(1)).addAccount(addAccountRequest);
        // Verify that the exception message contains the response message from the account service
        assertTrue(exception.getMessage().contains(responseEntity.getBody().getMessage()));
    }

    private CustomerRegistrationRequest createTestRegistrationRequest() {
        return new CustomerRegistrationRequest(1L, "John", "Doe", "123 Main St", 1, "john.doe@example.com", 2);
    }
}
