package com.emazon.stock.utils;

public class Constants {

    public static final Integer MIN_CHARACTERS_NAME_CATEGORY = 1;
    public static final Integer MAX_CHARACTERS_NAME_CATEGORY = 50;
    public static final Integer MIN_CHARACTERS_DESCRIPTION_CATEGORY = 1;
    public static final Integer MAX_CHARACTERS_DESCRIPTION_CATEGORY = 90;

    //Exceptions messages
    public static final String EXCEPTION_CATEGORY_NAME_NULL = "The name cannot be null";
    public static final String EXCEPTION_CATEGORY_NAME_SIZE = "The name must be between 1 and 50 characters";
    public static final String EXCEPTION_CATEGORY_DESCRIPTION_NULL = "The description cannot be null";
    public static final String EXCEPTION_CATEGORY_DESCRIPTION_SIZE = "The description must be between 1 and 90 characters";
}
