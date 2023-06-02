package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.RequestDTO.CardRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CardResponseDto;
import com.example.Caravan.Model.Card;

import java.util.Random;
import java.util.UUID;

public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .cvv(cardRequestDto.getCvv())
                .validTill(cardRequestDto.getValidTill())
                .build();
    }

    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .customerName(card.getCustomer().getName())
                .cardNo(card.getCardNo())
                .cardType(card.getCardType())
                .build();
    }
}
