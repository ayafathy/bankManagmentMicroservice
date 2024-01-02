package com.bank.customermanagement.facade;

import com.bank.customermanagement.client.AccountClient;
import com.bank.customermanagement.entity.Customer;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.model.AddAccountRequest;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.repository.CustomerRepository;
import com.bank.customermanagement.service.CustomerService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;
    private final  AccountClient accountClient;

    @Transactional
    public void createCustomer(CustomerRegistrationRequest request) throws BusinessExceptions {
    	String assciatedLegaleId =customerService.registerCustomer(request);
    	AddAccountRequest addAccountRequest =new AddAccountRequest(assciatedLegaleId,request.accountTypeId(),request.email());
    	accountClient.addAccount(addAccountRequest);
    	
    }
}
