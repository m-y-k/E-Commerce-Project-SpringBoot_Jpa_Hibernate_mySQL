package com.example.Caravan.DTO.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderResponseDto {
    String orderNo;

    Integer totalValue;

    Date orderDate;

    String cardUsed;

    String customerName;

    List<ItemResponseDto> items ;
}
