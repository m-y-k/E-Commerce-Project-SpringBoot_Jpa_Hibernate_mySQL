package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.ItemRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ItemResponseDto;
import com.example.Caravan.Model.Item;

public interface ItemServiceInt {
    public Item createItem(ItemRequestDto itemRequestDto);
}
