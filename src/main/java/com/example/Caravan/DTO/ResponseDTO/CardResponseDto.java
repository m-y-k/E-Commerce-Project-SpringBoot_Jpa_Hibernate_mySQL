package com.example.Caravan.DTO.ResponseDTO;

import com.example.Caravan.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardResponseDto {
    String customerName;

    String cardNo;

    CardType cardType;
}
