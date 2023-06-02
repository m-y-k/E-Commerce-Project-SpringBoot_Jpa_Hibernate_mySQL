package com.example.Caravan.DTO.RequestDTO;

import com.example.Caravan.Model.Cart;
import com.example.Caravan.Model.Product;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ItemRequestDto {

    Integer requiredQuantity;

    Integer productId;

    String customerEmailId;
}
