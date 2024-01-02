package com.bank.customermanagement.model;

public record AddAccountRequest(
		 String assciatedLegaleId,
		Integer accountTypeId,
		 String email) {
}
