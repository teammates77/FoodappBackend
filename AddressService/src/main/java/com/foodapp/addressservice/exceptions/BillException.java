package com.foodapp.addressservice.exceptions;

public class BillException extends RuntimeException{
    public BillException() {
    }

    public BillException(String message) {
        super(message);
    }
}
