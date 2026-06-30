package com.fintrack.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByDateTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Transaction> findByCategoryAndDateTimeBetween(Long categoryId, LocalDateTime startDate,
            LocalDateTime endDate);
}