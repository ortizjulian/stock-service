package com.emazon.stock.application.exception;

public class MissingAttributeException extends RuntimeException {
    public MissingAttributeException(String message) {
        super(message);
    }
}