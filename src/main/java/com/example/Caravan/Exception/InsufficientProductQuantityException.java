package com.example.Caravan.Exception;

public class InsufficientProductQuantityException extends RuntimeException{
    public InsufficientProductQuantityException(String productName){
        super("Insufficient quantity available for the product "+productName+"!");
    }
}
