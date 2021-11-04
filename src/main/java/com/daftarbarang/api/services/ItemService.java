package com.daftarbarang.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.daftarbarang.api.models.entities.Category;
import com.daftarbarang.api.models.entities.Item;
import com.daftarbarang.api.models.repos.ItemRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class ItemService {
    
    @Autowired
    private ItemRepo itemRepo;

    public Item insertOrUpdateItem (Item item) {
        return itemRepo.save(item);
    }

    public void deleteItemById (Long id) {
        itemRepo.deleteById(id);
    }

    public Item getItemById (Long id) {
        Optional<Item> item = itemRepo.findById(id);
        if (!item.isPresent()) return null;
        else return item.get();
    }

    public Iterable<Item> getAllItem () {
        return itemRepo.findAll();
    }

    public Iterable<Item> getAllItemByCategory (Category category) {
        return itemRepo.findByCategory(category);
    }

    public Item addSubtractItemNumber (Long id, Long number) {
        Optional<Item> item = itemRepo.findById(id);
        item.get().setNumber(item.get().getNumber() + number);
        return itemRepo.save(item.get());
    }

}
