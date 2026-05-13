package com.fintrack.demo.model.dto.transaction;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record TransactionUpdateRequestDTO(
    Long id,
    String name,
    String description,
    String payee,
    BigDecimal totalAmount
) { }
