package com.fintrack.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fintrack.demo.model.Item;
import com.fintrack.demo.model.Transaction;

public interface TransactionService {
    Transaction createTransaction(Transaction transaction);

    Transaction getTransactionById(Long id);
    List<Transaction> getTransactionsByPeriod(LocalDateTime startDate, LocalDateTime endDate);
    List<Transaction> getTransactionsByPeriodAndCategoryId(LocalDateTime startDate, LocalDateTime endDate, Long categoryId);

    Transaction updateTransaction(Transaction transaction);

    void deleteTransaction(Long id);

    Transaction addItemToTransaction(Long transactionId, Item item);
    Transaction removeItemFromTransaction(Long transactionId, Long itemId);
    Transaction updateItemInTransaction(Long transactionId, Item item);
}
