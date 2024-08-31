package com.emazon.stock.domain.exception;

public class PaginationParametersInvalidException extends RuntimeException {
    public PaginationParametersInvalidException(String message) {
        super(message);
    }
}