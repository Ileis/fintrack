package com.fintrack.demo.service;

import java.time.LocalDateTime;
import java.util.List;

import com.fintrack.demo.model.Item;

public interface ItemService {
    Item createItem(Item item);

    Item getItemById(Long id);
    List<Item> getItemsByPeriod(LocalDateTime startDate, LocalDateTime endDate);
    List<Item> getItemsByPeriodAndCategoryId(LocalDateTime startDate, LocalDateTime endDate, Long categoryId);

    Item updateItem(Item item);

    void deleteItem(Long id);

}
