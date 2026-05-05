package com.fintrack.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateAndTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByCategoryAndDateAndTimeBetween(Long categoryId, LocalDateTime startDate,
            LocalDateTime endDate);
}