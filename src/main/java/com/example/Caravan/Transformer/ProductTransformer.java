package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.RequestDTO.ProductRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.Enum.ProductStatus;
import com.example.Caravan.Model.Product;
import org.springframework.web.bind.annotation.RequestBody;

public class ProductTransformer {
    public static Product ProductRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .quantity(productRequestDto.getQuantity())
                .category(productRequestDto.getCategory())
                .status(ProductStatus.IN_STOCK)
                .build();
    }
    public static ProductResponseDto ProductToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .productStatus(product.getStatus())
                .quantity(product.getQuantity())
                .build();
    }
}
