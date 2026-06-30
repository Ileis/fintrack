package com.fintrack.demo.model;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fintrack.demo.dto.transaction.TransactionResponseDTO;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100, name = "name")
    private String name;

    @Column(length = 255, name = "description")
    private String description;

    @Column(nullable = false, name = "date_time")
    @Builder.Default
    private LocalDateTime dateTime = LocalDateTime.now();

    @Column(nullable = false, length = 50, name = "payee")
    private String payee;

    @Column(nullable = false, precision = 19, scale = 2, name = "total_amount")
    private BigDecimal totalAmount;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "transaction_id")
    @Builder.Default
    private List<Item> items = new ArrayList<Item>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;

    public BigDecimal getItemsPriceTotalSum() {
        if (this.items == null) return BigDecimal.ZERO;
        return this.items.stream().map(i -> i.getPrice()).reduce(BigDecimal.ZERO, (n1, n2) -> { return n1.add(n2); });
    }

    public TransactionResponseDTO toResponseDTO() {
        return TransactionResponseDTO.builder()
            .id(this.getId())
            .name(this.getName())
            .description(this.getDescription())
            .dateTime(this.getDateTime())
            .payee(this.getPayee())
            .totalAmount(this.getTotalAmount())
            .build();
    }
}