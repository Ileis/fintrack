package com.fintrack.demo.service;

import java.util.List;

import com.fintrack.demo.model.Category;
import com.fintrack.demo.model.enums.TypeCategory;

public interface CategoryService {
    Category createCategory(Category category);

    Category getCategoryById(Long id);
    List<Category> getCategoriesByType(TypeCategory typeCategory);

    Category updateCategory(Category category);

    void deleteCategory(Long id);
}
