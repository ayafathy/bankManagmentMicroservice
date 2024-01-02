package com.bank.accountmanagement.controller;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.accountmanagement.constant.BankConstant;
import com.bank.accountmanagement.exception.BusinessExceptions;
import com.bank.accountmanagement.facade.AccountFacade;
import com.bank.accountmanagement.model.AddAccountRequest;
import com.bank.accountmanagement.model.GeneralResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;


@Slf4j
@RestController
@RequestMapping("api/v1/account")
@AllArgsConstructor
public class AccountController {
    
    private final AccountFacade accountFacade;
    @Operation(summary = "add a account", description = "add a account")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully add account", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "200 Response",
                                    summary = "200 from the service directly",
                                    value = "{\r\n"
                                    		+ "  \"assciatedLegaleId\": \"1231241\",\r\n"
                                    		+ "  \"accountTypeId\": 1,\r\n"
                                    		+ "  \"email\": \"test@test.com\"\r\n"
                                    		+ "}")})),
            @ApiResponse(responseCode = "1", description = "Business Error - The account was not added", content = @Content(
                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                    examples = {
                            @ExampleObject(
                                    name = "1 Response",
                                    summary = "1 from the service directly",
                                    value = "{\n" +
                                            "  \"errors\": [\n" +
                                            BankConstant.CUSTOMER_ِALLOW_TOHAVE_ONLY_ONE_ACCOUNT_OF_SALARYTYPE +","+
                                             BankConstant.EXCCED_MAX_NUMBER_OF_ACCOUTS_FOR_CUSTOMER +
                                            "  ]\n" +
                                            "}")}))})
    @PostMapping
    public ResponseEntity<GeneralResponse>  addAccount(@Valid @RequestBody AddAccountRequest addAccountRequest) {
        log.info("new add Account {}", addAccountRequest);
      try {
    	  accountFacade.addAccount(addAccountRequest);
        return new GeneralResponse().response(null);
      }catch(BusinessExceptions businessExceptions) {
    	  log.error("businessExceptions  {}", businessExceptions);
    	  return new GeneralResponse().response(BankConstant.BuisnessFailureCode,businessExceptions.message ,null);
      }
      catch (Exception e) {
    	  log.error("Exception  {}", e);
    	  return new GeneralResponse().response(BankConstant.BuisnessFailureCode,BankConstant.GENERAL_MSG ,null);
	}
    }
}
