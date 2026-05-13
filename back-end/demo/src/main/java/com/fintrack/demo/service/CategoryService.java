package com.fintrack.demo.service;

import java.util.List;

import com.fintrack.demo.dto.category.CategoryRequestDTO;
import com.fintrack.demo.model.Category;
import com.fintrack.demo.model.enums.TypeCategory;

public interface CategoryService {
    Category createCategory(CategoryRequestDTO dto);

    Category getCategoryById(Long id);
    List<Category> getCategoriesByType(TypeCategory typeCategory);

    Category updateCategory(Long id, CategoryRequestDTO dto);

    void deleteCategory(Long id);
}
