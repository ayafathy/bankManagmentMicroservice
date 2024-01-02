package com.bank.accountmanagement.entity.lookup;

import jakarta.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"id"})
@Entity
@NoArgsConstructor
//should i add generate all?
public class AccountType extends LookupBase{
	public static final AccountType SALARY = new AccountType(1);
	public static final AccountType SAVING  = new AccountType(2);
	public static final AccountType INVESTMENT = new AccountType(3);
	  public AccountType(Integer id) {
	       super(id);
	    }
}
