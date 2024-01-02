package com.bank.accountmanagement.entity.lookup;

import jakarta.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"id"})
@Entity
@NoArgsConstructor

public class TransactionType extends LookupBase{

	public static final TransactionType credit = new TransactionType(1);
	public static final TransactionType debited = new TransactionType(2);
	  public TransactionType(Integer id) {
	       super(id);
	    }
}
