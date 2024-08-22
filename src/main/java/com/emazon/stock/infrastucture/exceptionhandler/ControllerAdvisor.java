package com.emazon.stock.infrastucture.exceptionhandler;
import com.emazon.stock.domain.exception.DataConstraintViolationException;
import com.emazon.stock.domain.exception.MissingAttributeException;
import com.emazon.stock.infrastucture.exception.CategoryAlreadyExistsException;
import com.emazon.stock.infrastucture.exception.CategoryNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.Map;

@ControllerAdvice
public class ControllerAdvisor {

    private static final String MESSAGE = "Message";

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<Map<String, String>> handleCategoryAlreadyExistsException(
            CategoryAlreadyExistsException categoryAlreadyExistsException) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Collections.singletonMap(MESSAGE, ExceptionResponse.CATEGORY_ALREADY_EXISTS.getMessage()));
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleCategoryNotFoundException(
            CategoryNotFoundException categoryNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Collections.singletonMap(MESSAGE, categoryNotFoundException.getMessage()));
    }

    @ExceptionHandler(MissingAttributeException.class)
    public ResponseEntity<Map<String, String>> handleMissingAttributeException(
            MissingAttributeException missingAttributeException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, missingAttributeException.getMessage()));
    }

    @ExceptionHandler(DataConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleDataConstraintViolationException(
            DataConstraintViolationException dataConstraintViolationException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Collections.singletonMap(MESSAGE, dataConstraintViolationException.getMessage()));
    }
}
