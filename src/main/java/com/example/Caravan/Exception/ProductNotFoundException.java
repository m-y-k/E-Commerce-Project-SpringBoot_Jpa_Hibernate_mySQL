package com.example.Caravan.Exception;

public class ProductNotFoundException extends RuntimeException{
    public ProductNotFoundException(){
        super("Product does not exist.");
    }
}
