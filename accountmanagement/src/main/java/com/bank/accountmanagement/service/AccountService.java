package com.bank.accountmanagement.service;

import java.math.BigDecimal;
import java.util.List;
import lombok.AllArgsConstructor;

import org.apache.commons.lang3.RandomStringUtils;


import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import com.bank.accountmanagement.constant.BankConstant;
import com.bank.accountmanagement.entity.Account;
import com.bank.accountmanagement.entity.lookup.AccountStatus;
import com.bank.accountmanagement.entity.lookup.AccountType;
import com.bank.accountmanagement.exception.BusinessExceptions;
import com.bank.accountmanagement.model.AddAccountRequest;
import com.bank.accountmanagement.model.AddAccountResponse;
import com.bank.accountmanagement.repository.AccountRepository;


@Service
@AllArgsConstructor
public class AccountService {

	private final AccountRepository accountRepository;
	private final PasswordEncoder  passwordEncoder  ;

	public void validateAddAccount(AddAccountRequest request) throws BusinessExceptions {
		List<Account> accounts = accountRepository.findByAccountNumberLikeIgnoreCase(request.assciatedLegaleId());

		if (accounts == null || accounts.size() == 0) {
			return;
		}
		if (accounts.size() >= BankConstant.MAX_NUMBER_OF_ACCOUTS_FOR_CUSTOMER) {
			throw new BusinessExceptions(BankConstant.EXCCED_MAX_NUMBER_OF_ACCOUTS_FOR_CUSTOMER);
		}
		// customer should only have account of salary type
		if (request.accountTypeId().equals(AccountType.SALARY.getId())) {
			long salarycount = accounts.stream()
					.filter(acc -> acc.getAccountType().getId().equals(AccountType.SALARY.getId())).count();
			if (salarycount > 0) {
				throw new BusinessExceptions(BankConstant.CUSTOMER_ِALLOW_TOHAVE_ONLY_ONE_ACCOUNT_OF_SALARYTYPE);

			}
		}
	}
    
    public AddAccountResponse addAccount(AddAccountRequest request) throws BusinessExceptions {
    	validateAddAccount(request);
    	String accountNumber =request.assciatedLegaleId()+RandomStringUtils.randomNumeric(BankConstant.ACCOUNT_NUMBER_REMAINING_SIZE);
    	String defaultPin =RandomStringUtils.randomNumeric(BankConstant.PASSWORD_LENGTH);
    	Account account = Account.builder()
                .accountNumber(accountNumber)
                .pin(passwordEncoder.encode(defaultPin))
                .balance(new BigDecimal(0))
                .accountType(new AccountType(request.accountTypeId()))
                .accountStatus(AccountStatus.ACTIVE_STATUS).build();
    	accountRepository.save(account);
    	return new AddAccountResponse(accountNumber,defaultPin,request.email());
        
    }
}
