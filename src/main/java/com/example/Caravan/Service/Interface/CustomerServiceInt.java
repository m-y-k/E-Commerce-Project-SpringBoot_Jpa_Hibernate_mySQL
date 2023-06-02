package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.CustomerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CustomerResponseDto;
import com.example.Caravan.DTO.ResponseDTO.SellerResponseDto;
import com.example.Caravan.Enum.Gender;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface CustomerServiceInt {
    public CustomerResponseDto addCustomer(@RequestBody CustomerRequestDto customerRequestDto);

    public List<CustomerResponseDto> getFemaleCustomersBetweenAgeXAndY(Gender gender , Integer X, Integer Y);

 //   List<CustomerResponseDto> getCustomersWithKProducts(Integer k);
}
