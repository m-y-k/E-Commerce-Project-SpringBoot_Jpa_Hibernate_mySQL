package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Model.Customer;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CheckOutCartRequestDto {
    String emailId;

    String cardNo;

    Integer cvv;
}
