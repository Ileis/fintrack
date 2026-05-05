package com.fintrack.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale.Category;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fintrack.demo.exception.BusinessException;
import com.fintrack.demo.exception.ResourceNotFoundException;
import com.fintrack.demo.model.Item;
import com.fintrack.demo.model.Transaction;
import com.fintrack.demo.repository.CategoryRepository;
import com.fintrack.demo.repository.ItemRepository;
import com.fintrack.demo.repository.TransactionRepository;
import com.fintrack.demo.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;
    private final ItemRepository itemRepository;

    @Override
    public Transaction createTransaction(Transaction transaction) {
        if (transaction.getId() != null)
            throw new IllegalArgumentException("New transaction cannot have an ID");

        if (transaction.getTotalAmount().compareTo(BigDecimal.ZERO) < 0)
            throw new IllegalArgumentException("Total amount cannot be negative");

        return transactionRepository.save(transaction);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction do not exists"));
    }

    @Override
    public List<Transaction> getTransactionsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate))
            throw new BusinessException("start date must be before end date");

        return transactionRepository.findByDateAndTimeBetween(startDate, endDate);
    }

    @Override
    public List<Transaction> getTransactionsByPeriodAndCategoryId(LocalDateTime startDate,
            LocalDateTime endDate,
            Long categoryId) {
        if (startDate.isAfter(endDate))
            throw new BusinessException("start date must be before end date");

        if (categoryRepository.findById(categoryId).isEmpty())
            throw new ResourceNotFoundException("Category do not exists");

        return transactionRepository.findByCategoryAndDateAndTimeBetween(categoryId, startDate, endDate);
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
