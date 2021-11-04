package com.daftarbarang.api.services;

import java.util.Optional;

import javax.transaction.Transactional;

import com.daftarbarang.api.models.entities.Category;
import com.daftarbarang.api.models.repos.CategoryRepo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CategoryService {
    @Autowired
    private CategoryRepo categoryRepo;

    public Category insertOrUpdateCategory (Category category) {
        return categoryRepo.save(category);
    }

    public void deleteCategoryById (Long id) {
        categoryRepo.deleteById(id);
    }

    public Category getCategoryById (Long id) {
        Optional<Category> category = categoryRepo.findById(id);
        if (!category.isPresent()) return null;
        else return category.get();
    }

    public Iterable<Category> getAllCategory () {
        return categoryRepo.findAll();
    }

}
