package com.emazon.stock.domain.exception;

public class DataConstraintViolationException extends RuntimeException   {
    public DataConstraintViolationException(String message) {
        super(message);
    }
}
