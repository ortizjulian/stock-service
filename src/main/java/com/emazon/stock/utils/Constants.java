package com.emazon.stock.utils;

public class Constants {
    private Constants () {
        throw new UnsupportedOperationException(UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED);
    }

    public static final String UTILITY_CLASS_SHOULD_NOT_BE_INSTANTIATED = "Utility class should not be instantiated";

    public static final int MIN_CHARACTERS_NAME_CATEGORY = 1;
    public static final int MAX_CHARACTERS_NAME_CATEGORY = 50;
    public static final int MIN_CHARACTERS_DESCRIPTION_CATEGORY = 1;
    public static final int MAX_CHARACTERS_DESCRIPTION_CATEGORY = 90;

    public static final int MIN_CHARACTERS_NAME_BRAND = 1;
    public static final int MAX_CHARACTERS_NAME_BRAND = 50;
    public static final int MIN_CHARACTERS_DESCRIPTION_BRAND = 1;
    public static final int MAX_CHARACTERS_DESCRIPTION_BRAND = 120;

    public static final int MIN_ARTICLE_QUANTITY = 0;
    public static final String MIN_ARTICLE_PRICE = "0.0";
    public static final int MIN_ARTICLE_CATEGORIES = 1;
    public static final int MAX_ARTICLE_CATEGORIES = 3;
    public static final String DEFAULT_BRAND_NAME = "";
    public static final String DEFAULT_CATEGORY_NAME = "";

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
    public static final String EXCEPTION_CATEGORY_NAME_NULL = "The category name cannot be null";
    public static final String EXCEPTION_CATEGORY_NAME_SIZE = "The category name must be between 1 and 50 characters";
    public static final String EXCEPTION_CATEGORY_DESCRIPTION_NULL = "The category description cannot be null";
    public static final String EXCEPTION_CATEGORY_DESCRIPTION_SIZE = "The category description must be between 1 and 90 characters";
    public static final String EXCEPTION_CATEGORY_ALREADY_EXISTS = "There is already a category with that name";
    public static final String EXCEPTION_CATEGORY_NOT_FOUND_BY_NAME = "Category not found with name: ";
    public static final String EXCEPTION_CATEGORY_NOT_FOUND_BY_ID = "Category not found with ID: ";
    //BrandExceptions
    public static final String EXCEPTION_BRAND_NAME_NULL = "The brand name cannot be null";
    public static final String EXCEPTION_BRAND_NAME_SIZE = "The brand name must be between 1 and 50 characters";
    public static final String EXCEPTION_BRAND_DESCRIPTION_NULL = "The brand description cannot be null";
    public static final String EXCEPTION_BRAND_DESCRIPTION_SIZE = "The brand description must be between 1 and 120 characters";
    public static final String EXCEPTION_BRAND_ALREADY_EXISTS = "There is already a brand with that name";
    public static final String EXCEPTION_BRAND_NOT_FOUND_BY_NAME = "Brand not found with name: ";
    public static final String EXCEPTION_BRAND_NOT_FOUND_BY_ID = "Brand not found with ID: ";
    //ArticleExceptions
    public static final String EXCEPTION_ARTICLE_NAME_BLANK = "The article name cannot be blank";
    public static final String EXCEPTION_ARTICLE_DESCRIPTION_BLANK = "The article name cannot be blank";
    public static final String EXCEPTION_ARTICLE_QUANTITY_NEGATIVE = "The article quantity must be a non-negative number";
    public static final String EXCEPTION_ARTICLE_PRICE_NEGATIVE = "The article price must be a positive number";
    public static final String EXCEPTION_ARTICLE_BRAND_NULL = "The article brand cannot be null";
    public static final String EXCEPTION_ARTICLE_CATEGORIES_NULL = "The article categories cannot be null";
    public static final String EXCEPTION_ARTICLE_CATEGORIES_SIZE = "At least one and at most three categories must be assigned";
    public static final String EXCEPTION_DUPLICATE_CATEGORIES = "Duplicate categories found in the article.";
    //Pagination Exceptions
    public static final String EXCEPTION_PAGE_NUMBER_NEGATIVE = "Page number cannot be negative.";
    public static final String EXCEPTION_PAGE_SIZE_NEGATIVE = "Page size cannot be negative.";
    public static final String EXCEPTION_SORT_DIRECTION_INVALID = "Sort direction must be 'ASC' or 'DESC'.";
}


