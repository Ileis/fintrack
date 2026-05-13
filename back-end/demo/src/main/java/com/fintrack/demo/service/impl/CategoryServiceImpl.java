package com.fintrack.demo.service.impl;


import java.util.List;

import com.fintrack.demo.dto.category.CategoryRequestDTO;
import com.fintrack.demo.model.Category;
import com.fintrack.demo.model.enums.TypeCategory;
import com.fintrack.demo.service.CategoryService;

public class CategoryServiceImpl implements CategoryService {
    @Override
    public Category createCategory(CategoryRequestDTO dto) {
        return null;
    }

    @Override
    public Category getCategoryById(Long id) {
        return null;
    }

    @Override
    public List<Category> getCategoriesByType(TypeCategory typeCategory) {
        return null;
    }

    @Override
    public Category updateCategory(Long id, CategoryRequestDTO dto) {
        return null;
    }

    @Override
    public void deleteCategory(Long id) {
    }
}
