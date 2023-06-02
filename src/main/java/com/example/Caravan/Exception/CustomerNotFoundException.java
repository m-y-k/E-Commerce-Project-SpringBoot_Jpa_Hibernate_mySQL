package com.example.Caravan.Exception;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(){
        super("Customer does not exist.");
    }
}
