package com.example.Caravan.Controller;

import com.example.Caravan.DTO.RequestDTO.CustomerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CustomerResponseDto;
import com.example.Caravan.Enum.Gender;
import com.example.Caravan.Service.ServiceImp.CustomerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    CustomerServiceImpl customerService;
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto = customerService.addCustomer(customerRequestDto);
        return new ResponseEntity(customerResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/get-female-customers-between-age-x-and-y")
    public ResponseEntity getFemaleCustomersBetweenAgeXAndY(@RequestParam Gender gender, @RequestParam Integer X, @RequestParam Integer Y){
        List<CustomerResponseDto> customerResponseDtos = customerService.getFemaleCustomersBetweenAgeXAndY(gender, X, Y);
        return new ResponseEntity(customerResponseDtos,HttpStatus.OK);
    }

   /* @GetMapping("/Customers-who-have-ordered-K-products")
    public ResponseEntity getCustomersWithKProducts(@RequestParam Integer K){
        List<CustomerResponseDto> customerResponseDtos = customerService.getCustomersWithKProducts(K);
        return new ResponseEntity(customerResponseDtos,HttpStatus.OK);
    }*/
}
