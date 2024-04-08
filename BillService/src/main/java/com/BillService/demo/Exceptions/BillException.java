package com.BillService.demo.Exceptions;

public class BillException extends RuntimeException{
    public BillException() {
    }

    public BillException(String message) {
        super(message);
    }
}
