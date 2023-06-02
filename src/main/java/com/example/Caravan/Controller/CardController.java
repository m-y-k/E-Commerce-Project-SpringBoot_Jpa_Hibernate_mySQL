package com.example.Caravan.Controller;

import com.example.Caravan.DTO.RequestDTO.CardRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CardResponseDto;
import com.example.Caravan.Exception.CardTypeNotFoundException;
import com.example.Caravan.Exception.CustomerNotFoundException;
import com.example.Caravan.Service.ServiceImp.CardServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/card")
public class CardController {

    @Autowired
    CardServiceImpl cardService;
    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto){
        try {
            CardResponseDto cardResponseDto = cardService.addCard(cardRequestDto);
            return new ResponseEntity(cardResponseDto, HttpStatus.CREATED);
        }
        catch(CardTypeNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
