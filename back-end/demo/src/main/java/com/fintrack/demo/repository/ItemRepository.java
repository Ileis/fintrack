package com.fintrack.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {
    public List<Item> findByTransactionId(Long id);
}