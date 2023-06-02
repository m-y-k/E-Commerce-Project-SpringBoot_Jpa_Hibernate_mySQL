package com.example.Caravan.Transformer;

import com.example.Caravan.DTO.RequestDTO.SellerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.SellerResponseDto;
import com.example.Caravan.Model.Seller;
import org.springframework.web.bind.annotation.RequestBody;

public class SellerTransformer {

    public static Seller sellerRequestDtoToSeller( SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .emailId(sellerRequestDto.getEmailId())
                .mobNo(sellerRequestDto.getMobNo())
                .gender((sellerRequestDto.getGender()))
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto( Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .mobNo(seller.getMobNo())
                .build();
    }
}
