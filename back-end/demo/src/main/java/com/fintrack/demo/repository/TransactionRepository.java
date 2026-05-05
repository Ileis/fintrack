package com.fintrack.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
}
