package com.bank.accountmanagement.facade;

import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

import com.bank.accountmanagement.constant.BankConstant;
import com.bank.accountmanagement.exception.BusinessExceptions;
import com.bank.accountmanagement.model.AddAccountRequest;
import com.bank.accountmanagement.model.AddAccountResponse;
import com.bank.accountmanagement.model.EmailModel;
import com.bank.accountmanagement.service.AccountService;
import com.bank.accountmanagement.service.RabbitMQSender;
@Service
@AllArgsConstructor
public class AccountFacade {

	private final AccountService accountService;
    private final RabbitMQSender rabbitMQSender ;
  

    public void addAccount(AddAccountRequest request) throws BusinessExceptions {
    	AddAccountResponse  addAccountResponse =accountService.addAccount(request);
        sendSuccessCreateAccountEmail(addAccountResponse);
    }


	private void sendSuccessCreateAccountEmail(AddAccountResponse addAccountResponse) {
	 String body =BankConstant.Account_created_successfully+addAccountResponse.accountNumber()
	 + BankConstant.DEFAULT_PIN + addAccountResponse.pin();
	 rabbitMQSender.sendEmail( new EmailModel(body,addAccountResponse.email()));
		
	}
}
