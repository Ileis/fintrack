package com.fintrack.demo.model.enums;

public enum TypeCategory {
    ITEM("ITEM"),
    TRANSACTION("TRANSACTION");
    
    private final String value;

    TypeCategory(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}