package com.emazon.stock.domain.utils;

import com.emazon.stock.domain.exception.PaginationParametersInvalidException;

public class PaginationValidator {

    private static final String SORT_DIRECTION_ASC = "ASC";
    private static final String SORT_DIRECTION_DESC = "DESC";
    private static final String[] ALLOWED_SORT_BY = {"name", "description"};

    public static void validatePagination(int page, int size, String sortDirection, String sortBy) {
        if (page < 0) {
            throw new PaginationParametersInvalidException("Page number cannot be negative.");
        }
        if (size < 0) {
            throw new PaginationParametersInvalidException("Page size cannot be negative.");
        }
        if (sortDirection == null || sortDirection.isEmpty() ||
                (!SORT_DIRECTION_ASC.equals(sortDirection) && !SORT_DIRECTION_DESC.equals(sortDirection))) {
            throw new PaginationParametersInvalidException("Sort direction must be 'ASC' or 'DESC'.");
        }
        if (sortBy == null || sortBy.isEmpty() || !isValidSortBy(sortBy)) {
            throw new PaginationParametersInvalidException("Sort by must be one of the following: 'name', 'description'.");
        }
    }

    private static boolean isValidSortBy(String sortBy) {
        for (String validSortBy : ALLOWED_SORT_BY) {
            if (validSortBy.equals(sortBy)) {
                return true;
            }
        }
        return false;
    }
}

