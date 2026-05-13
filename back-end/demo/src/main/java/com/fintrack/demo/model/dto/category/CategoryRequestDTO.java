package com.fintrack.demo.model.dto.category;

import com.fintrack.demo.model.enums.TypeCategory;

public record CategoryRequestDTO(
    String name,
    String color,
    String icon,
    TypeCategory typeCategory
) { }
