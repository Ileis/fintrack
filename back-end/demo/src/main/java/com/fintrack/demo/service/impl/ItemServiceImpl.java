package com.fintrack.demo.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fintrack.demo.exception.ResourceNotFoundException;
import com.fintrack.demo.model.Item;
import com.fintrack.demo.repository.ItemRepository;
import com.fintrack.demo.service.ItemService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService {
    private final ItemRepository itemRepository;

    @Override
    public Item getItemById(Long id) {
        return itemRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Item not found"));
    }

    @Override
    public List<Item> getItemsByPeriod(LocalDateTime startDate, LocalDateTime endDate) {
        return null;
    }

    @Override
    public List<Item> getItemsByPeriodAndCategoryId(LocalDateTime startDate, LocalDateTime endDate, Long categoryId) {
        return null;
    }
}
