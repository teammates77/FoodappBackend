package com.foodapp.foodcartservice.exceptions;

public class RestaurantException extends RuntimeException{
    public RestaurantException() {
    }

    public RestaurantException(String message) {
        super(message);
    }
}
