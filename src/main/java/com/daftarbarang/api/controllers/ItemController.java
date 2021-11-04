package com.daftarbarang.api.controllers;

import javax.validation.Valid;

import com.daftarbarang.api.dto.AddSubtractItemNumberDTO;
import com.daftarbarang.api.dto.ItemDTO;
import com.daftarbarang.api.dto.ResponseData;
import com.daftarbarang.api.models.entities.Item;
import com.daftarbarang.api.services.CategoryService;
import com.daftarbarang.api.services.ItemService;

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
@RequestMapping ("/api/items")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<ResponseData<Item>> insertItem (@Valid @RequestBody ItemDTO itemDTO, Errors errors) {
        ResponseData<Item> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseData.setValue(null);
            responseData.setCompleted(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        else {
            Item item = modelMapper.map(itemDTO, Item.class);
            responseData.getMessage().add("Successfully Added");
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.setValue(itemService.insertOrUpdateItem(item));
            responseData.setCompleted(true);
            return ResponseEntity.ok(responseData);
        }
    }

    @PutMapping
    public ResponseEntity<ResponseData<Item>> updateItem (@Valid @RequestBody Item item, Errors errors) {
        ResponseData<Item> responseData = new ResponseData<>();

        if (errors.hasErrors()) {
            for (ObjectError error : errors.getAllErrors()) {
                responseData.getMessage().add(error.getDefaultMessage());
            }
            responseData.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseData.setValue(null);
            responseData.setCompleted(false);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
        else {
            responseData.getMessage().add("Successfully Updated");
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.setValue(itemService.insertOrUpdateItem(item));
            responseData.setCompleted(true);
            return ResponseEntity.ok(responseData);
        }
    }

    @PostMapping("/addnumber")
    public ResponseEntity<ResponseData<Item>> addNumber (@RequestBody AddSubtractItemNumberDTO bodyRequest) {
        ResponseData<Item> responseData = new ResponseData<>();
        try {
            responseData.setValue(itemService.addSubtractItemNumber(bodyRequest.getId(), bodyRequest.getNumber()));
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.getMessage().add("Number successfully added");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseData.getMessage().add("Number failed to be added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }
    @PostMapping("/subtractnumber")
    public ResponseEntity<ResponseData<Item>> subtractNumber (@RequestBody AddSubtractItemNumberDTO bodyRequest) {
        ResponseData<Item> responseData = new ResponseData<>();
        try{
            responseData.setValue(itemService.addSubtractItemNumber(bodyRequest.getId(), bodyRequest.getNumber()));
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.getMessage().add("Number successfully added");
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_REQUEST.toString());
            responseData.getMessage().add("Number failed to be added");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseData);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseData<Item>> deleteItemById (@PathVariable("id") Long id) {
        ResponseData<Item> responseData = new ResponseData<>();
        try {
            responseData.setValue(itemService.getItemById(id));
            itemService.deleteItemById(id);
            responseData.getMessage().add("Successfully deleted");
            responseData.setCompleted(true);
            responseData.setStatus(HttpStatus.OK.toString());
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.getMessage().add("Failed to be deleted");
            responseData.setCompleted(false);
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
        
    } 

    @GetMapping("/{id}")
    public ResponseEntity<ResponseData<Item>> getItemById (@PathVariable("id") Long id) {
        ResponseData<Item> responseData = new ResponseData<>();
        try {
            responseData.setValue(itemService.getItemById(id));
            responseData.getMessage().add("Successfully Received");
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.setCompleted(true);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.getMessage().add("Failed to Received");
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            responseData.setCompleted(false);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
    }

    @GetMapping
    public ResponseEntity<ResponseData<Iterable<Item>>> getAllItem () {
        ResponseData<Iterable<Item>> responseData = new ResponseData<>();
        try {
            responseData.setValue(itemService.getAllItem());
            responseData.getMessage().add("Successfully Received");
            responseData.setStatus(HttpStatus.OK.toString());
            responseData.setCompleted(true);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.getMessage().add("Failed to Received");
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            responseData.setCompleted(false);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
    }

    @GetMapping("/bycategory/{id}")
    public ResponseEntity<ResponseData<Iterable<Item>>> getAllItemByCategory (@PathVariable("id") Long id) {
        ResponseData<Iterable<Item>> responseData = new ResponseData<>();
        try {
            responseData.setValue(itemService.getAllItemByCategory(categoryService.getCategoryById(id)));
            responseData.getMessage().add("Successfully Received");
            responseData.setStatus(HttpStatus.OK.toString());   
            responseData.setCompleted(true);
            return ResponseEntity.ok(responseData);
        } catch (Exception e) {
            responseData.setValue(null);
            responseData.getMessage().add("Failed to Received");
            responseData.setStatus(HttpStatus.BAD_GATEWAY.toString());
            responseData.setCompleted(false);
            return ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(responseData);
        }
    }
}
