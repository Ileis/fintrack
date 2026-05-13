package com.fintrack.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findByTransactionId(Long id);

    List<Item> findByDateAndTimeBetween(LocalDateTime startDate, LocalDateTime endDate);

    List<Item> findByCategoryAndDateAndTimeBetween(Long categoryId, LocalDateTime startDate,
            LocalDateTime endDate);
}