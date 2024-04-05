package com.foodapp.orderdetails.exceptions;

public class OrderException extends RuntimeException{
    public OrderException() {
    }

    public OrderException(String message) {
        super(message);
    }
}
