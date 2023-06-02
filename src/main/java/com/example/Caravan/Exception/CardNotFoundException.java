package com.example.Caravan.Exception;

public class CardNotFoundException extends RuntimeException{
    public CardNotFoundException(){
        super("Sorry ! You cannot use this card.");
    }
}
