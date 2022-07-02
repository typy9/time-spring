package com.parpiiev.time.exceptions.category;

public class InvalidCategoryException extends RuntimeException{

    public InvalidCategoryException() {
    }

    public InvalidCategoryException(String message) {
        super(message);
    }

}
