package com.fintrack.demo.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintrack.demo.dto.category.CategoryRequestDTO;
import com.fintrack.demo.exception.ResourceNotFoundException;
import com.fintrack.demo.model.Category;
import com.fintrack.demo.model.enums.TypeCategory;
import com.fintrack.demo.repository.CategoryRepository;
import com.fintrack.demo.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    private void updateFields(Category c, CategoryRequestDTO dto) {
        c.setName(dto.name());
        c.setColor(dto.color());
        c.setIcon(dto.icon());
        c.setTypeCategory(dto.typeCategory());
    }

    @Override
    @Transactional
    public Category createCategory(CategoryRequestDTO dto) {
        Category c = new Category();
        updateFields(c, dto);

        return categoryRepository.save(c);
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    @Override
    public List<Category> getCategoriesByType(TypeCategory typeCategory) {
        return categoryRepository.findAllByTypeCategory(typeCategory);
    }

    @Override
    @Transactional
    public Category updateCategory(Long id, CategoryRequestDTO dto) {
        Category c = categoryRepository.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException("Category not found"));

        updateFields(c, dto);

        return c;
    }

    @Override
    @Transactional
    public void deleteCategory(Long id) {
        if (categoryRepository.existsById(id))
            throw new ResourceNotFoundException("Category not found");

        categoryRepository.deleteById(id);
    }
}
