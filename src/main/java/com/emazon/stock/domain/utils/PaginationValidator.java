package com.emazon.stock.domain.utils;

import com.emazon.stock.domain.exception.PaginationParametersInvalidException;
import com.emazon.stock.utils.Constants;

public class PaginationValidator {

    private PaginationValidator () {
        throw new UnsupportedOperationException(Constants.UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static void validatePagination(Integer page, Integer size, String sortDirection) {
        if (page < 0) {
            throw new PaginationParametersInvalidException(Constants.EXCEPTION_PAGE_NUMBER_NEGATIVE);
        }
        if (size < 0) {
            throw new PaginationParametersInvalidException(Constants.EXCEPTION_PAGE_SIZE_NEGATIVE);
        }
        if (sortDirection == null || sortDirection.isEmpty() ||
                (!Constants.SORT_DIRECTION_ASC.equals(sortDirection) && !Constants.SORT_DIRECTION_DESC.equals(sortDirection))) {
            throw new PaginationParametersInvalidException(Constants.EXCEPTION_SORT_DIRECTION_INVALID);
        }
    }
}

