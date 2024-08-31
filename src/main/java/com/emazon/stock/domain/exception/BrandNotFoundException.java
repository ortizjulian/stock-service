package com.emazon.stock.domain.exception;

public class BrandNotFoundException extends  RuntimeException {
    public BrandNotFoundException(String message) {
        super(message);
    }
}
