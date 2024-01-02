package com.bank.customermanagement.facade;

import com.bank.customermanagement.client.AccountClient;
import com.bank.customermanagement.constant.BankConstant;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.model.AddAccountRequest;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.model.GeneralResponse;
import com.bank.customermanagement.service.CustomerService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Slf4j
@Service
@AllArgsConstructor
public class CustomerFacade {

    private final CustomerService customerService;
    private final  AccountClient accountClient;

    @Transactional
    public void createCustomer(CustomerRegistrationRequest request) throws BusinessExceptions {
    	String assciatedLegaleId =customerService.registerCustomer(request);
    	AddAccountRequest addAccountRequest =new AddAccountRequest(assciatedLegaleId,request.accountTypeId(),request.email());
    	ResponseEntity<GeneralResponse>  reponse	=accountClient.addAccount(addAccountRequest);
    	if(!reponse.getStatusCode().equals(HttpStatus.OK)) {
    		log.error(reponse.toString()+ BankConstant.FAILURE_IN_ACCOUNT_SERVICE);
    		throw new BusinessExceptions(BankConstant.FAILURE_IN_ACCOUNT_SERVICE);
    	}
    	if(!reponse.getBody().getCode().equals(200)) {
    		log.error(reponse.toString());
    		throw new BusinessExceptions(reponse.getBody().getMessage());
    	}
    }
}
