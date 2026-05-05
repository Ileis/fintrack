package com.fintrack.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
