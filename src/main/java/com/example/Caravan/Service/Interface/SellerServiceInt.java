package com.example.Caravan.Service.Interface;

import com.example.Caravan.DTO.RequestDTO.SellerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.DTO.ResponseDTO.SellerResponseDto;
import com.example.Caravan.Enum.Category;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface SellerServiceInt {
    public SellerResponseDto addSeller(@RequestBody SellerRequestDto sellerRequestDto);

    public String modifyBy(String emailId, String newEmailId);

    public List<SellerResponseDto> getProductCategorySellers(Category category);

    public List<ProductResponseDto> getProductsBySellerAndCategory(String sellerEmailId, Category productCategory);

    public List<SellerResponseDto> getSellersWithMostProducts();

    public List<SellerResponseDto> getSellersWithLeastProducts();

    public List<SellerResponseDto> getSellersWithCostliestProduct();

    public List<SellerResponseDto> getSellersWithCheapestProduct();
}
