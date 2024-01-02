package com.bank.accountmanagement.model;

import org.hibernate.validator.constraints.Length;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record AddAccountRequest(
		@NotNull @Length(max =7 ,min=7 ,message = "assciatedLegaleId must be 7 characters long.") String assciatedLegaleId,
		@NotNull Integer accountTypeId,
		@Email String email) {
}
