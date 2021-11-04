package com.daftarbarang.api.models.repos;

import com.daftarbarang.api.models.entities.Category;

import org.springframework.data.repository.CrudRepository;

public interface CategoryRepo extends CrudRepository <Category, Long>{
    
}
