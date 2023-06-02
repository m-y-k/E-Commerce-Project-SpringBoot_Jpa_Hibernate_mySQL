package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Enum.CardType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CardRequestDto {
    String cardNo;

    Integer cvv;

    Date validTill;

    CardType cardType;

    String customerEmailId;
}
