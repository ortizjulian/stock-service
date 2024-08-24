package com.emazon.stock.utils;

public class Constants {

    public static final Integer MIN_CHARACTERS_NAME_CATEGORY = 1;
    public static final Integer MAX_CHARACTERS_NAME_CATEGORY = 50;
    public static final Integer MIN_CHARACTERS_DESCRIPTION_CATEGORY = 1;
    public static final Integer MAX_CHARACTERS_DESCRIPTION_CATEGORY = 90;

    // Pagination defaults
    public static final String DEFAULT_PAGE = "0";
    public static final String DEFAULT_SIZE = "10";
    public static final String DEFAULT_SORT_DIRECTION = "ASC";
    public static final String DEFAULT_SORT_BY = "name";
    public static final String SORT_DIRECTION_ASC = "ASC";
    public static final String SORT_DIRECTION_DESC = "DESC";

    //Exceptions messages
    public static final String RESPONSE_MESSAGE_KEY = "Message";
    //CategoryExceptions
    public static final String EXCEPTION_CATEGORY_NAME_NULL = "The name cannot be null";
    public static final String EXCEPTION_CATEGORY_NAME_SIZE = "The name must be between 1 and 50 characters";
    public static final String EXCEPTION_CATEGORY_DESCRIPTION_NULL = "The description cannot be null";
    public static final String EXCEPTION_CATEGORY_DESCRIPTION_SIZE = "The description must be between 1 and 90 characters";
    public static final String EXCEPTION_CATEGORY_ALREADY_EXISTS = "There is already a category with that name";
    public static final String EXCEPTION_CATEGORY_NOT_FOUND = "Category not found with name: ";
    //Pagination Exceptions
    public static final String EXCEPTION_PAGE_NUMBER_NEGATIVE = "Page number cannot be negative.";
    public static final String EXCEPTION_PAGE_SIZE_NEGATIVE = "Page size cannot be negative.";
    public static final String EXCEPTION_SORT_DIRECTION_INVALID = "Sort direction must be 'ASC' or 'DESC'.";
}


