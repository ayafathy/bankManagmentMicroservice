package com.bank.customermanagement.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRegistrationRequest(
		Long id ,
		@NotNull String firstName,
		@NotNull String lastName,
		@NotNull   String address ,
		@NotNull   Integer customerTypeId ,
		@Email String email,
		@NotNull   Integer accountTypeId ) {
}
