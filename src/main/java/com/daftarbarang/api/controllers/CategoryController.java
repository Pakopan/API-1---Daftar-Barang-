package com.daftarbarang.api.controllers;

import javax.validation.Valid;

import com.daftarbarang.api.dto.CategoryDTO;
import com.daftarbarang.api.dto.ResponseData;
import com.daftarbarang.api.models.entities.Category;
import com.daftarbarang.api.services.CategoryService;

import org.modelmapper.ModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Category>> insertCategory (@Valid @RequestBody CategoryDTO categoryDTO, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseData.setValue(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        else {
            Category category = modelMapper.map(categoryDTO, Category.class);
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.setValue(categoryService.insertOrUpdateCategory(category));
            responseData.getMessage().add("Successfully added");
            return ResponseEntity.ok(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Category>> updateCategory (@Valid @RequestBody Category category, Errors errors) {
        ResponseData<Category> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseData.setValue(null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        else {
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.setCompleted(true);
            responseData.setValue(categoryService.insertOrUpdateCategory(category));
            responseData.getMessage().add("Successfully updated");
            return ResponseEntity.ok(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> deleteCategoryById (@PathVariable("id") Long id) {
        ResponseData<Category> responseData = new ResponseData<>();
        try {
            responseData.setValue(categoryService.getCategoryById(id));
            categoryService.deleteCategoryById(id);
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.getMessage().add("Successfully deleted");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            responseData.getMessage().add("Failed to be deleted");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
        
    } 

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Category>> getCategoryById (@PathVariable("id") Long id) {
        ResponseData<Category> responseData = new ResponseData<>();
        try {
            responseData.setValue(categoryService.getCategoryById(id));
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.getMessage().add("Successfully received");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            responseData.getMessage().add("Failed to be received");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Category>>> getAllCategory () {
        ResponseData<Iterable<Category>> responseData = new ResponseData<>();
        try {
            responseData.setValue(categoryService.getAllCategory());
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.getMessage().add("Successfully received");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            responseData.getMessage().add("Failed to be received");
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
        
    }

}
