package com.example.Caravan.DTO.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CartResponseDto {

    Integer cartTotal;

    String customerName;

    List<ItemResponseDto> items;
}
