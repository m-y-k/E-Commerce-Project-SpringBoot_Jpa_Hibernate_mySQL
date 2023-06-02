package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.ItemRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CartResponseDto;
import com.example.Caravan.Model.Cart;
import com.example.Caravan.Model.Customer;
import com.example.Caravan.Model.Item;
import org.springframework.web.bind.annotation.RequestBody;

public interface CartServiceInt {
    public Cart addCart(Customer customer);
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto);

}
