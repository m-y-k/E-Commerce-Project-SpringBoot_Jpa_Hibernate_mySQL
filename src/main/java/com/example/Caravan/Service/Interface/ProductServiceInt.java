package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.ProductRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.Enum.Category;
import com.example.Caravan.Enum.ProductStatus;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface ProductServiceInt {
    public ProductResponseDto addProduct(@RequestBody ProductRequestDto productRequestDto);

    public List<ProductResponseDto> getProductsByCategory(Category category);

    public List<ProductResponseDto> getProductsByCategoryAndPriceAboveK(Category category, Integer k);

    public List<ProductResponseDto> getTop5CheapestProductsByCategory(Category category);

    public List<ProductResponseDto> getTop5CostliestProductsByCategory(Category category);

    public List<ProductResponseDto> getProductsByCategoryAndStatus(Category category, ProductStatus status);
}
