package com.fintrack.demo.dto.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record TransactionRequestDTO(
    String name,
    String description,
    LocalDateTime dateTime,
    String payee,
    BigDecimal totalAmount
) { }
