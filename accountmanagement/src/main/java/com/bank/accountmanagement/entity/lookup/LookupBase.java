package com.bank.accountmanagement.entity.lookup;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
@Data
@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"id"})
public abstract class LookupBase {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
	public LookupBase(Integer id) {
		super();
		this.id = id;
	}
    
 
}
