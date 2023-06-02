package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)

public class ProductRequestDto {
    String name;

    Integer price;

    Integer quantity;

    Category category;

    String sellerEmailId;
}
