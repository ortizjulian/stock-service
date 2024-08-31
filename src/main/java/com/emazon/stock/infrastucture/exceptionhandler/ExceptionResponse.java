package com.emazon.stock.infrastucture.exceptionhandler;

import com.emazon.stock.utils.Constants;

public enum ExceptionResponse {
    CATEGORY_ALREADY_EXISTS(Constants.EXCEPTION_CATEGORY_ALREADY_EXISTS),
    BRAND_ALREADY_EXISTS(Constants.EXCEPTION_BRAND_ALREADY_EXISTS),
    DUPLICATE_CATEGORY_EXCEPTION(Constants.EXCEPTION_DUPLICATE_CATEGORIES);
    private String message;

    ExceptionResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
}