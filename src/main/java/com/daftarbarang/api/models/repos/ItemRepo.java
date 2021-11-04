package com.daftarbarang.api.models.repos;

import com.daftarbarang.api.models.entities.Category;
import com.daftarbarang.api.models.entities.Item;

import org.springframework.data.repository.CrudRepository;

public interface ItemRepo extends CrudRepository<Item, Long>{
    Iterable<Item> findByCategory(Category category);
}
