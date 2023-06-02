package com.example.Caravan.Controller;

import com.example.Caravan.DTO.RequestDTO.CheckOutCartRequestDto;
import com.example.Caravan.DTO.RequestDTO.ItemRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CartResponseDto;
import com.example.Caravan.DTO.ResponseDTO.ItemResponseDto;
import com.example.Caravan.DTO.ResponseDTO.OrderResponseDto;
import com.example.Caravan.Exception.CustomerNotFoundException;
import com.example.Caravan.Exception.InsufficientProductQuantityException;
import com.example.Caravan.Exception.ProductNotFoundException;
import com.example.Caravan.Model.Cart;
import com.example.Caravan.Model.Item;
import com.example.Caravan.Service.ServiceImp.CartServiceImpl;
import com.example.Caravan.Service.ServiceImp.ItemServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    ItemServiceImpl itemService;
    @Autowired
    CartServiceImpl cartService;
    @PostMapping("/add-item")
    public ResponseEntity addItemToCart(@RequestBody ItemRequestDto itemRequestDto){
        try {
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item, itemRequestDto);
            return new ResponseEntity(cartResponseDto,HttpStatus.OK);
        }
        catch(ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch(CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch(InsufficientProductQuantityException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")
    public ResponseEntity checkoutCart(@RequestBody CheckOutCartRequestDto checkoutCartRequestDto){

        try{
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkoutCartRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }
}
