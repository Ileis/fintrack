package com.fintrack.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fintrack.demo.dto.item.ItemRequestDTO;
import com.fintrack.demo.dto.transaction.TransactionRequestDTO;
import com.fintrack.demo.dto.transaction.TransactionResponseDTO;
import com.fintrack.demo.model.Item;
import com.fintrack.demo.model.Transaction;

public interface TransactionService {
    TransactionResponseDTO createTransaction(TransactionRequestDTO transaction);

    Transaction getTransactionById(Long id);
    List<Transaction> getTransactionsByPeriod(LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> getTransactionsByPeriodAndCategoryId(LocalDateTime startDate, LocalDateTime endDate, Long categoryId);

    TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO transaction);

    void deleteTransaction(Long id);

    Item addItemToTransaction(Long transactionId, ItemRequestDTO item);
    void removeItemFromTransaction(Long transactionId, Long itemId);
    Item updateItemInTransaction(Long transactionId, Long itemId, ItemRequestDTO item);
}
