package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.OrderRequestDto;
import com.example.Caravan.DTO.ResponseDTO.OrderResponseDto;

public interface OrderServiceInt {
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto);
}
