package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.CustomerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.CustomerResponseDto;
import com.example.Caravan.Enum.Gender;
import com.example.Caravan.Model.Cart;
import com.example.Caravan.Model.Customer;
import com.example.Caravan.Repository.CustomerRepository;
import com.example.Caravan.Service.Interface.CustomerServiceInt;
import com.example.Caravan.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerServiceInt {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    CartServiceImpl cartService;
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto){
        //RequestDto to Entity
        Customer customer = CustomerTransformer.CustomerRequestDtoToCustomer(customerRequestDto);
        Cart cart = cartService.addCart(customer);
        customer.setCart(cart);

        //save the Entity

        Customer savedCustomer = customerRepository.save(customer); // saves both cart and customer.

        //convert Entity to ResponseDto
        return CustomerTransformer.CustomerToCustomerResponseDto(customer);
    }

    public List<CustomerResponseDto> getFemaleCustomersBetweenAgeXAndY(Gender gender, Integer X, Integer Y) {
        List<Customer> customers = customerRepository.findByGenderAndAgeBetween(String.valueOf(gender),X,Y);
        List<CustomerResponseDto> customerResponseDtos = new ArrayList<>();

        for(Customer customer : customers){
            CustomerResponseDto customerResponseDto = CustomerTransformer.CustomerToCustomerResponseDto(customer);
            customerResponseDtos.add(customerResponseDto);
        }
        return customerResponseDtos;
    }
    /*public List<CustomerResponseDto> getCustomersWithKProducts(Integer k){

    }*/
}
