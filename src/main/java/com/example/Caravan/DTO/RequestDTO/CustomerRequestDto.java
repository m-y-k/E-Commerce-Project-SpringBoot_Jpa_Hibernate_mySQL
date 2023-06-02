package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CustomerRequestDto {
    String name;

    String mobNo;

    String emailId;

    Gender gender;

    Integer age;
}
