package com.bank.customermanagement.entity.lookup;

import jakarta.persistence.Entity;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
@EqualsAndHashCode(of = {"id"})
@Entity
@NoArgsConstructor
public class CustomerStatus extends LookupBase{
	public static final CustomerStatus DRAFT_STATUS = new CustomerStatus(1);
	public static final CustomerStatus ACTIVE_STATUS = new CustomerStatus(2);
	public static final CustomerStatus IN_ACTIVE_STATUS = new CustomerStatus(3);
	  public CustomerStatus(Integer id) {
	       super(id);
	    }
}
