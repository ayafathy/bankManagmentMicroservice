package com.bank.accountmanagement.entity.lookup;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(of = {"id"})
@Entity
@NoArgsConstructor
public class TransactionStatus extends LookupBase{
	public static final TransactionStatus SUCCESS_STATUS = new TransactionStatus(1);
	public static final TransactionStatus FAILURE_STATUS = new TransactionStatus(2);
	  public TransactionStatus(Integer id) {
	       super(id);
	    }
}
