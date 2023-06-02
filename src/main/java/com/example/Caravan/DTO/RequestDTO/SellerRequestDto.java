package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Enum.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SellerRequestDto {
    String name;

    String mobNo;

    String emailId;

    Gender gender;
}
