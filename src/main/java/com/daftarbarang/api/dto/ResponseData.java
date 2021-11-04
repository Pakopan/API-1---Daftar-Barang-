package com.daftarbarang.api.dto;

import java.util.ArrayList;
import java.util.List;

public class ResponseData <T>{
    private String status;
    private boolean completed;
    private List<String> message = new ArrayList<>();
    private T value;
    
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public List<String> getMessage() {
        return message;
    }
    public void setMessage(List<String> message) {
        this.message = message;
    }
    public T getValue() {
        return value;
    }
    public void setValue(T value) {
        this.value = value;
    }

    
}
