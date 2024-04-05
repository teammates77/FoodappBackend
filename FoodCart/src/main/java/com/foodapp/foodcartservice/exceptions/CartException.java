package com.foodapp.foodcartservice.exceptions;

public class CartException extends RuntimeException{
    public CartException() {
    }

    public CartException(String message) {
        super(message);
    }
}
