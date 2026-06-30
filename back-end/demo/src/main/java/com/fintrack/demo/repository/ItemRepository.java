package com.fintrack.demo.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fintrack.demo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    @Query(
        "SELECT i " +
        "FROM Transaction t " +
        "JOIN t.items i " +
        "WHERE t.id = :id"
    )
    List<Item> findByTransactionId(@Param("id") Long id);

    @Query(
        "SELECT i " +
        "FROM Item i " +
        "JOIN FETCH Transaction t " +
        "WHERE t.dateTime BETWEEN :startDate AND :endDate"
    )
    List<Item> findByDateTimeBetween(@Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query(
        "SELECT i " +
        "FROM Item i " +
        "JOIN FETCH Transaction t " +
        "WHERE t.dateTime BETWEEN :startDate AND :endDate AND i.category.id = :categoryId"
    )
    List<Item> findByCategoryAndDateTimeBetween(@Param("categoryId") Long categoryId, @Param("startDate") LocalDateTime startDate,
            @Param("endDate") LocalDateTime endDate);
}