package com.example.Caravan.Exception;

import java.util.Objects;

public class SellerNotFoundException extends RuntimeException{
    public SellerNotFoundException(){
        super("Seller does not exist");
    }
}
