package com.bank.accountmanagement.entity.lookup;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(of = {"id"})
@Entity
@NoArgsConstructor
public class AccountStatus extends LookupBase{
	public static final AccountStatus ACTIVE_STATUS = new AccountStatus(1);
	public static final AccountStatus IN_ACTIVE_STATUS = new AccountStatus(2);
	  public AccountStatus(Integer id) {
	       super(id);
	    }
}
