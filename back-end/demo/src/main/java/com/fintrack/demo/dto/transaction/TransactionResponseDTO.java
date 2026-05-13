package com.fintrack.demo.dto.transaction;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.Builder;

@Builder
public record TransactionResponseDTO(
    Long id,
    String name,
    String description,
    LocalDateTime dateAndTime,
    String payee,
    BigDecimal totalAmount
) { }
