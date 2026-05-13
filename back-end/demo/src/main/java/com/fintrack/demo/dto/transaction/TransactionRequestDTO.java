package com.fintrack.demo.dto.transaction;

import java.math.BigDecimal;

import lombok.Builder;

@Builder
public record TransactionRequestDTO(
    String name,
    String description,
    String payee,
    BigDecimal totalAmount
) { }
