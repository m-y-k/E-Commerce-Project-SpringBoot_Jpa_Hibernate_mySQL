package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Model.Product;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderRequestDto {

    String customerEmailId;

    String cardNo;

    Integer cardCvv;

    Integer productId;

    Integer quantity;
}
