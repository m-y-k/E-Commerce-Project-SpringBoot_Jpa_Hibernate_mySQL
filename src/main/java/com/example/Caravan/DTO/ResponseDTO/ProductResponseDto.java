package com.example.Caravan.DTO.ResponseDTO;

import com.example.Caravan.Enum.Category;
import com.example.Caravan.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductResponseDto {
    String productName;

    String sellerName;

    Category category;

    Integer price;

    ProductStatus productStatus;

    Integer quantity;
}
