package com.fintrack.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fintrack.demo.model.Item;
import com.fintrack.demo.model.Transaction;
import com.fintrack.demo.repository.ItemRepository;
import com.fintrack.demo.repository.TransactionRepository;
import com.fintrack.demo.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final ItemRepository itemRepository;
    
    @Override
    public Transaction createTransaction(Transaction transaction) {
        return null;
    }
    
    @Override
    public Transaction getTransactionById(Long id) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public List<Transaction> getTransactionsByPeriodAndCategoryId(LocalDateTime startDate,
                                                                  LocalDateTime endDate,
                                                                  Long categoryId) {
        return null;
    }

    @Override
    public Transaction updateTransaction(Transaction transaction) {
        return null;
    }

    @Override
    public void deleteTransaction(Long id) {
    }

    @Override
    public Transaction addItemToTransaction(Long transactionId, Item item) {
        return null;
    }

    @Override
    public Transaction removeItemFromTransaction(Long transactionId, Long itemId) {
        return null;
    }

    @Override
    public Transaction updateItemInTransaction(Long transactionId, Item item) {
        return null;
    }
}
