package com.example.Caravan.Exception;

public class CardTypeNotFoundException extends RuntimeException{
    public CardTypeNotFoundException(){
        super("CardType is invalid!");
    }
}
