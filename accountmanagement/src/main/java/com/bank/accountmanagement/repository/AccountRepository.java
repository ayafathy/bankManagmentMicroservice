package com.bank.accountmanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.accountmanagement.entity.Account;


public interface AccountRepository extends JpaRepository<Account, Integer> {
 List<Account> findByAccountNumberLikeIgnoreCase(String assciatedLegaleId);

}
