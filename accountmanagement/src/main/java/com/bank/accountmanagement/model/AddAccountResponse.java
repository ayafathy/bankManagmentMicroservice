package com.bank.accountmanagement.model;


public record AddAccountResponse(	
		 String accountNumber,
		 String pin,
		 String email) {
}
