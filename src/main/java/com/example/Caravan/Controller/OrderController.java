package com.example.Caravan.Controller;

import com.example.Caravan.DTO.RequestDTO.CheckOutCartRequestDto;
import com.example.Caravan.DTO.RequestDTO.OrderRequestDto;
import com.example.Caravan.DTO.ResponseDTO.OrderResponseDto;
import com.example.Caravan.Service.ServiceImp.CartServiceImpl;
import com.example.Caravan.Service.ServiceImp.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderServiceImpl orderService;
    @PostMapping("/place-order")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto){
        try {
            OrderResponseDto orderResponseDto = orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

}
