package com.fintrack.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fintrack.demo.model.Category;
import java.util.List;
import com.fintrack.demo.model.enums.TypeCategory;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    List<Category> findAllByTypeCategory(TypeCategory typeCategory);
}
