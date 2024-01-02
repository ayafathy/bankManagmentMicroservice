package com.bank.customermanagement.entity.lookup;

import jakarta.persistence.Entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(of = {"id"})
@Entity
@NoArgsConstructor
//should i add generate all?
public class CustomerType extends LookupBase{
	public static final CustomerType RETAIL = new CustomerType(1);
	public static final CustomerType CORPORATE = new CustomerType(2);
	public static final CustomerType INVERSTMENT = new CustomerType(3);
	  public CustomerType(Integer id) {
	       super(id);
	    }
}
