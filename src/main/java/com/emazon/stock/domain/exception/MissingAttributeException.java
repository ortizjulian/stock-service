package com.emazon.stock.domain.exception;

public class MissingAttributeException extends RuntimeException {
    public MissingAttributeException(String message) {
        super(message);
    }
}