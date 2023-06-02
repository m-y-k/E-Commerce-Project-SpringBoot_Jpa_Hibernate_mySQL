package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.RequestDTO.CustomerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CustomerResponseDto;
import com.example.Caravan.Model.Customer;
import org.springframework.web.bind.annotation.RequestBody;

public class CustomerTransformer {
    public static Customer CustomerRequestDtoToCustomer( CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .age(customerRequestDto.getAge())
                .build();
    }

    public static CustomerResponseDto CustomerToCustomerResponseDto( Customer customer){
        return CustomerResponseDto.builder()
                .name(customer.getName())
                .emailId(customer.getEmailId())
                .build();
    }
}
