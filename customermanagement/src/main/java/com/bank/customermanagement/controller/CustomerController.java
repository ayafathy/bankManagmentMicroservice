package com.bank.customermanagement.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.customermanagement.constant.BankConstant;
import com.bank.customermanagement.exception.BusinessExceptions;
import com.bank.customermanagement.facade.CustomerFacade;
import com.bank.customermanagement.model.CustomerRegistrationRequest;
import com.bank.customermanagement.model.GeneralResponse;
import com.bank.customermanagement.service.CustomerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;


@Slf4j
@RestController
@RequestMapping("api/v1/customers")
@AllArgsConstructor
public class CustomerController {
    
    private final CustomerFacade customerFacade;
    @Operation(summary = "Create a Customer", description = "Create a Customer")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully created Customer", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "200 from the service directly",
                                    value = "{\r\n"
                                    		+ "  \"id\": 0,\r\n"
                                    		+ "  \"firstName\": \"aya\",\r\n"
                                    		+ "  \"lastName\": \"aya\",\r\n"
                                    		+ "  \"address\": \"string\",\r\n"
                                    		+ "  \"customerTypeId\": 1,\r\n"
                                    		+ "  \"email\": \"test@test.com\",\r\n"
                                    		+ "  \"accountTypeId\": 1\r\n"
                                    		+ "}")})),
            @ApiResponse(responseCode = "1", description = "Business Error - The customer was not created", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "1 Response",
                                    summary = "1 from the service directly",
                                    value = "{\n" +
                                            "  \"errors\": [\n" +
                                            "    \"customer email already Exist \",\n" +
                                            
                                            "  ]\n" +
                                            "}")}))})
    @PostMapping
    public ResponseEntity<GeneralResponse>  createCustomer(@Valid @RequestBody CustomerRegistrationRequest customerRegistrationRequest) {
        log.info("new customer registration {}", customerRegistrationRequest);
      try {
    	  customerFacade.createCustomer(customerRegistrationRequest);
        return new GeneralResponse().response(BankConstant.SUCCESS_MSG);
      }catch(BusinessExceptions businessExceptions) {
    	  return new GeneralResponse().response(BankConstant.BuisnessFailureCode,businessExceptions.message ,null);
      }
    }
}
