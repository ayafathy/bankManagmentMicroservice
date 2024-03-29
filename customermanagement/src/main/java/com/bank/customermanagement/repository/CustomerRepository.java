package com.bank.customermanagement.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bank.customermanagement.entity.Customer;


public interface CustomerRepository extends JpaRepository<Customer, Integer> {
 Optional<Customer> findByEmail(String email);
 Optional<Customer> findByAssciatedLegaleId(String assciatedLegaleId);
}
