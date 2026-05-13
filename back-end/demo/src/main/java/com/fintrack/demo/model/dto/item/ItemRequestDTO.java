package com.fintrack.demo.model.dto.item;

import java.math.BigDecimal;

import com.fintrack.demo.model.Category;

public record ItemRequestDTO(
    String name,
    BigDecimal price,
    Category category
) { }
