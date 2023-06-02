package com.example.Caravan.DTO.ResponseDTO;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerResponseDto {
    String name;

    String mobNo;

    String emailId;
}
