package com.bank.accountmanagement.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

import com.bank.accountmanagement.entity.lookup.AccountType;
import com.bank.accountmanagement.entity.lookup.TransactionStatus;
import com.bank.accountmanagement.entity.lookup.TransactionType;
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
public class Transaction {

    @Id
    @GeneratedValue
    private Integer id;
    private BigDecimal amount;
    private String AccountNumber;
    private String pin ;
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account customerType ;
    @ManyToOne
    @JoinColumn(name = "TRANSACTION_TYPE_ID")
    private TransactionType transactionType ;
    @ManyToOne
    @JoinColumn(name = "TRANSACTION_STATUS_ID")
    private TransactionStatus transactionStatus ;
}
