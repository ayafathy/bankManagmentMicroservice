package com.bank.customermanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.bank.customermanagement.constant.BankConstant;
import com.bank.customermanagement.model.GeneralResponse;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
    	log.error("An unexpected error occurred " +ex.getMessage() );
        return new ResponseEntity<>("An unexpected error occurred " , HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(BusinessExceptions.class)
    public ResponseEntity<GeneralResponse> handleCustomException(BusinessExceptions ex) {
          return new GeneralResponse().response(BankConstant.BuisnessFailureCode,ex.message ,null);
    }
}
