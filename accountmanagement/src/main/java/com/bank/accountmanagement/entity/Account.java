package com.bank.accountmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.bank.accountmanagement.entity.lookup.AccountType;
import com.bank.accountmanagement.entity.lookup.AccountStatus;

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
public class Account {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal balance;
    private String accountNumber;
    private String pin ;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_TYPE_ID")
    private AccountType accountType ;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_STATUS_ID")
    private AccountStatus accountStatus ;

}
