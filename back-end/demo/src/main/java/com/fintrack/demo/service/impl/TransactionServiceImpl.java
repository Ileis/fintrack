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
import com.fintrack.demo.dto.item.ItemRequestDTO;
import com.fintrack.demo.dto.transaction.TransactionRequestDTO;
import com.fintrack.demo.dto.transaction.TransactionResponseDTO;
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

    private void updateFields(Transaction t, TransactionRequestDTO dto) {
        t.setName(dto.name());
        t.setDescription(dto.description());
        t.setPayee(dto.payee());
        t.setTotalAmount(dto.totalAmount());
    }

    private void updateFields(Item i, ItemRequestDTO dto) {
        i.setName(dto.name());
        i.setPrice(dto.price());
        i.setCategory(dto.category());
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
    public TransactionResponseDTO createTransaction(TransactionRequestDTO dto) {

        if (dto.totalAmount().compareTo(BigDecimal.ZERO) < 0)
            throw new BusinessException("Total amount cannot be negative");

        Transaction t = Transaction.builder()
                .name(dto.name())
                .description(dto.description())
                .dateAndTime(LocalDateTime.now())
                .payee(dto.payee())
                .totalAmount(dto.totalAmount())
                .build();

        t = transactionRepository.save(t);
        return toTransactionResponse(t);
    }

    @Override
    public Transaction getTransactionById(Long id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction does not exists"));
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
    public TransactionResponseDTO updateTransaction(Long id, TransactionRequestDTO dto) {
        Transaction t = transactionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        if (t.getItemsPriceTotalSum().compareTo(dto.totalAmount()) > 0)
            throw new BusinessException("New total amount is less than items price sum");

        updateFields(t, dto);

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
    public Item addItemToTransaction(Long transactionId, ItemRequestDTO dto) {
        Transaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        BigDecimal itemAmount = transaction.getItemsPriceTotalSum();

        if (itemAmount.add(dto.price()).compareTo(transaction.getTotalAmount()) > 0)
            throw new BusinessException("Item amount is greater than transaction amount");

        Item i = Item.builder()
                .name(dto.name())
                .price(dto.price())
                .category(dto.category())
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
    public Item updateItemInTransaction(Long transactionId, Long itemId, ItemRequestDTO itemDto) {
        Transaction t = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new ResourceNotFoundException("Transaction not found"));

        Item i = t.getItems().stream()
                .filter((x) -> x.getId().equals(itemId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Item is not in Transaction"));

        BigDecimal itemsPriceTotalSum = t.getItemsPriceTotalSum();

        if (itemsPriceTotalSum.subtract(i.getPrice()).add(itemDto.price()).compareTo(t.getTotalAmount()) > 0)
            throw new BusinessException("New item value exceeds the transaction total amount");

        updateFields(i, itemDto);

        return i;
    }
}
