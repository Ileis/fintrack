package com.fintrack.demo.model.dto.transaction;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record TransactionRequestDTO(
    String name,
    String description,
    String payee,
    BigDecimal totalAmount
) { }
