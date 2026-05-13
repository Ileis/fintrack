package com.fintrack.demo.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintrack.demo.exception.BusinessException;
import com.fintrack.demo.exception.ResourceNotFoundException;
import com.fintrack.demo.model.Item;
import com.fintrack.demo.model.Transaction;
import com.fintrack.demo.model.dto.item.ItemRequestDTO;
import com.fintrack.demo.model.dto.transaction.TransactionRequestDTO;
import com.fintrack.demo.model.dto.transaction.TransactionResponseDTO;
import com.fintrack.demo.repository.CategoryRepository;
import com.fintrack.demo.repository.TransactionRepository;
import com.fintrack.demo.service.TransactionService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final CategoryRepository categoryRepository;

    private void updateFields(Transaction t, TransactionRequestDTO req) {
        t.setName(req.name());
        t.setDescription(req.description());
        t.setPayee(req.payee());
        t.setTotalAmount(req.totalAmount());
    }

    private TransactionResponseDTO toTransactionResponse(Transaction t) {
        return TransactionResponseDTO.builder()
                .id(t.getId())
                .name(t.getName())
                .description(t.getDescription())
                .dateAndTime(t.getDateAndTime())
                .payee(t.getPayee())
                .totalAmount(t.getTotalAmount())
                .build();
    }

    @Override
    @Transactional
    public TransactionResponseDTO createTransaction(TransactionRequestDTO req) {

        if (req.totalAmount().compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessException("Total amount cannot be negative");

        Transaction t = Transaction.builder()
                .name(req.name())
                .description(req.description())
                .dateAndTime(LocalDateTime.now())
                .payee(req.payee())
                .totalAmount(req.totalAmount())
                .build();

        t = transactionRepository.save(t);
        return toTransactionResponse(t);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction do not exists"));
    }

    @Override
    public List<Transaction> getTransactionsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate))
            throw new BusinessException("Start date must be before end date");

        return transactionRepository.findByDateAndTimeBetween(startDate, endDate);
    }

    @Override
    public List<Transaction> getTransactionsByPeriodAndCategoryId(LocalDateTime startDate,
            LocalDateTime endDate,
            Long categoryId) {
        if (startDate.isAfter(endDate))
            throw new BusinessException("Start date must be before end date");

        if (!categoryRepository.existsById(categoryId))
            throw new ResourceNotFoundException("Category do not exists");

        return transactionRepository.findByCategoryAndDateAndTimeBetween(categoryId, startDate, endDate);
    }

    @Override
    @Transactional
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO req) {
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        updateFields(t, req);

        return toTransactionResponse(transactionRepository.save(t));
    }

    @Override
    @Transactional
    public void deleteTransaction(Long id) {
        if (!transactionRepository.existsById(id))
            throw new ResourceNotFoundException("Transaction not found");
        transactionRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Item addItemToTransaction(Long transactionId, ItemRequestDTO req) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        BigDecimal itemAmount = transaction.getItems().stream().map(Item::getPrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (itemAmount.add(req.price()).compareTo(transaction.getTotalAmount()) > 0)
            throw new BusinessException("Item amount is greater than transaction amount");

        Item i = Item.builder()
                .name(req.name())
                .price(req.price())
                .category(req.category())
                .build();

        transaction.getItems().add(i);

        transactionRepository.save(transaction);

        return i;
    }

    @Override
    @Transactional
    public void removeItemFromTransaction(Long transactionId, Long itemId) {
        Transaction t = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (!t.getItems().removeIf((item) -> item.getId().equals(itemId)))
            throw new ResourceNotFoundException("Item not found");
    }

    @Override
    @Transactional
    public Item updateItemInTransaction(Long transactionId, ItemRequestDTO item) {
        return null;
    }
}
