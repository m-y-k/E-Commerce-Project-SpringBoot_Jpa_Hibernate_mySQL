package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.RequestDTO.OrderRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ItemResponseDto;
import com.example.Caravan.DTO.ResponseDTO.OrderResponseDto;
import com.example.Caravan.Model.Customer;
import com.example.Caravan.Model.Item;
import com.example.Caravan.Model.OrderEntity;
import com.example.Caravan.Model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class OrderTransformer {
    public static OrderEntity orderRequestDtoToOrder(OrderRequestDto orderRequestDto, Customer customer,Item item, String maskedCard)    {
        return OrderEntity.builder()
                .customer(customer)
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .cardUsed(maskedCard)
                .orderNo(String.valueOf(UUID.randomUUID()))
                .items(new ArrayList<>())
                .build();
    }
    public static OrderResponseDto orderToOrderResponseDto(OrderEntity order){
        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item : order.getItems())
            itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(item));


        return OrderResponseDto.builder()
                .orderNo(order.getOrderNo())
                .orderDate(order.getOrderDate())
                .totalValue(order.getTotalValue())
                .customerName(order.getCustomer().getName())
                .cardUsed(order.getCardUsed())
                .items(itemResponseDtoList)
                .build();
    }
}
