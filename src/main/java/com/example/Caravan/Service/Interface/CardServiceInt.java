package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.CardRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CardResponseDto;

public interface CardServiceInt {
   public  CardResponseDto addCard(CardRequestDto cardRequestDto);
}
