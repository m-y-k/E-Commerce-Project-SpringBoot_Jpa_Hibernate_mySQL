package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.RequestDTO.ItemRequestDto;
import com.example.Caravan.DTO.RequestDTO.OrderRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ItemResponseDto;
import com.example.Caravan.Model.Customer;
import com.example.Caravan.Model.Item;
import com.example.Caravan.Model.Product;

public class ItemTransformer {
    public static Item itemRequestDtoToItem(ItemRequestDto itemRequestDto){
        return Item.builder()
                .requiredQuantity(itemRequestDto.getRequiredQuantity())
                .build();
    }

    public static Item itemRequestDtoToItem(OrderRequestDto orderRequestDto, Product product){
        return Item.builder()
                .requiredQuantity(orderRequestDto.getQuantity())
                .product(product)
                .build();
    }

    public static ItemResponseDto itemToItemResponseDto(Item item){
        return ItemResponseDto.builder()
                .quantity(item.getRequiredQuantity())
                .itemPrice(item.getProduct().getPrice())
                .itemName(item.getProduct().getName())
                .build();
    }
}
