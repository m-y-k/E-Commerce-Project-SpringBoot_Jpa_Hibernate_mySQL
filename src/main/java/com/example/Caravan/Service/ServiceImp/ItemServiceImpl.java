package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.ItemRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ItemResponseDto;
import com.example.Caravan.Enum.ProductStatus;
import com.example.Caravan.Exception.CustomerNotFoundException;
import com.example.Caravan.Exception.InsufficientProductQuantityException;
import com.example.Caravan.Exception.ProductNotFoundException;
import com.example.Caravan.Model.Customer;
import com.example.Caravan.Model.Item;
import com.example.Caravan.Model.Product;
import com.example.Caravan.Repository.CustomerRepository;
import com.example.Caravan.Repository.ProductRepository;
import com.example.Caravan.Service.Interface.ItemServiceInt;
import com.example.Caravan.Transformer.ItemTransformer;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemServiceInt {
    @Autowired
    CustomerRepository customerRepository;
    @Autowired
    ProductRepository productRepository;
    public Item createItem(ItemRequestDto itemRequestDto){
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(Objects.isNull(customer))
            throw new CustomerNotFoundException();

        /*Product product = productRepository.findById(itemRequestDto.getProductId()).get();
        if(Objects.isNull(product))
            throw new ProductNotFoundException();*/

        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty())
            throw new ProductNotFoundException();

        Product product = productOptional.get();

        Integer availableProductQuantity = product.getQuantity();
        if(availableProductQuantity < itemRequestDto.getRequiredQuantity())
            throw new InsufficientProductQuantityException(product.getName());


        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto);

        return item;
    }
}
