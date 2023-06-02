package com.example.Caravan.Exception;

public class InvalidProductStatusException extends RuntimeException{
    public InvalidProductStatusException(){
        super("Invalid Product Status!");
    }
}
