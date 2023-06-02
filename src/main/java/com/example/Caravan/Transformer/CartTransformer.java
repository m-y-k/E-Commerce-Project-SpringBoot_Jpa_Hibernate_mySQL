package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.ResponseDTO.CartResponseDto;
import com.example.Caravan.DTO.ResponseDTO.ItemResponseDto;
import com.example.Caravan.Model.Cart;
import com.example.Caravan.Model.Item;

import java.util.ArrayList;
import java.util.List;

public class CartTransformer {
    public static CartResponseDto cartToCartResponseDto(Cart cart){
        List<Item> itemList = cart.getItems();
        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();

        for(Item item : itemList){
            itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}
