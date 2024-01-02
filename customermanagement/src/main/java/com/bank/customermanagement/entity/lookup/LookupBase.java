package com.bank.customermanagement.entity.lookup;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import com.bank.customermanagement.model.LookupBaseDTO;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.GenerationType;

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
