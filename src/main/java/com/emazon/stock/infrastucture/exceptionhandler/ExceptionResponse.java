package com.emazon.stock.infrastucture.exceptionhandler;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS("There is already a category with that name");
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}