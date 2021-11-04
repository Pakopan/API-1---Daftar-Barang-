package com.daftarbarang.api.dto;

import javax.validation.constraints.NotEmpty;

import com.daftarbarang.api.models.entities.Category;

public class ItemDTO {
    @NotEmpty (message = "Name is required")
    private String name;

    private String description;
    
    private Long number;

    private Category category;


    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    

}
