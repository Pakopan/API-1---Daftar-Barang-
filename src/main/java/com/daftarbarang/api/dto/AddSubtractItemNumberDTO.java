package com.daftarbarang.api.dto;

public class AddSubtractItemNumberDTO {
    private Long id;
    private Long number;
    
    public AddSubtractItemNumberDTO() {
    }

    public AddSubtractItemNumberDTO(Long id, Long number) {
        this.id = id;
        this.number = number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    
    
}
