package com.bank.customermanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import com.bank.customermanagement.entity.lookup.CustomerStatus;
import com.bank.customermanagement.entity.lookup.CustomerType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {

    @Id
    @GeneratedValue
    private Integer id;
    private String firstName;
    private String lastName;
    private String address ;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_TYPE_ID")
    private CustomerType customerType ;
    @ManyToOne
    @JoinColumn(name = "CUSTOMER_STATUS_ID")
    private CustomerStatus customerStatus ;
    @Column( unique = true)
    private String email ;
    @Column( unique = true)
    private String assciatedLegaleId;
}
