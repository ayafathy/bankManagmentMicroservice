package com.bank.customermanagement.service;

import com.bank.customermanagement.constant.BankConstant;
import com.bank.customermanagement.entity.Customer;
import com.bank.customermanagement.entity.lookup.CustomerStatus;
import com.bank.customermanagement.entity.lookup.CustomerType;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.repository.CustomerRepository;

import lombok.AllArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import java.util.Optional;
@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    
    private void validateregisterCustomer(CustomerRegistrationRequest request) throws BusinessExceptions {
    	Optional<Customer> customer = customerRepository.findByEmail(request.email());
    	if(!customer.isEmpty()) {
    		throw new BusinessExceptions(BankConstant.CUSTOMER_ALREADY_EXIST);
    	}
    	
    }
    
    
    public String  registerCustomer(CustomerRegistrationRequest request) throws BusinessExceptions {
    	validateregisterCustomer( request) ;
    	String assciatedLegaleId =RandomStringUtils.randomNumeric(BankConstant.ASSOCIATED_LEGALID_LENGTH);
    	Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .address(request.address())
                .assciatedLegaleId(assciatedLegaleId)
                .customerType(new CustomerType(request.customerTypeId()))
                .customerStatus(CustomerStatus.DRAFT_STATUS).build();
        customerRepository.save(customer);
        return assciatedLegaleId;
    }
}
