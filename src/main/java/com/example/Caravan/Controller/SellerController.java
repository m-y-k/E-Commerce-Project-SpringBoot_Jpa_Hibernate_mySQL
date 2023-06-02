package com.example.Caravan.Controller;

import com.example.Caravan.DTO.RequestDTO.SellerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.DTO.ResponseDTO.SellerResponseDto;
import com.example.Caravan.Enum.Category;
import com.example.Caravan.Exception.CategoryNotFoundException;
import com.example.Caravan.Exception.SellerNotFoundException;
import com.example.Caravan.Model.Seller;
import com.example.Caravan.Service.ServiceImp.SellerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/seller")
public class SellerController {
    @Autowired
    SellerServiceImpl sellerService;

    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto = sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity(sellerResponseDto, HttpStatus.CREATED);
    }

    @PutMapping("/modify-by")
    public ResponseEntity modifyBy(@RequestParam("emailId") String emailId, @RequestParam("newEmailId") String newEmailId){
        try{
            String response = sellerService.modifyBy(emailId, newEmailId);
            return new ResponseEntity(response, HttpStatus.OK);
        }
        catch(SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-sellers-by-product-category")
    public ResponseEntity getSellersByProductCategory(@RequestParam("category")Category category){
        try{
            List<SellerResponseDto> sellers =  sellerService.getProductCategorySellers(category);
            return new ResponseEntity(sellers,HttpStatus.OK);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-products-of-a-category-by-a-seller")
    public ResponseEntity getProductsBySellerAndCategory(@RequestParam("emailId") String sellerEmailId, @RequestParam("category") Category productCategory){
        try{
            List<ProductResponseDto> products = sellerService.getProductsBySellerAndCategory(sellerEmailId,productCategory);
            return new ResponseEntity(products, HttpStatus.OK);
        }
        catch(SellerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-sellers-with-most-products")
    public ResponseEntity getSellersWithMostProducts(){
        List<SellerResponseDto> sellerResponseDtos = sellerService.getSellersWithMostProducts();
        return new ResponseEntity(sellerResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-sellers-with-least-products")
    public ResponseEntity getSellersWithLeastProducts(){
        List<SellerResponseDto> sellerResponseDtos = sellerService.getSellersWithLeastProducts();
        return new ResponseEntity(sellerResponseDtos, HttpStatus.OK);
    }

    @GetMapping("/get-sellers-with-costliest-product")
    public ResponseEntity getSellersWithCostliestProduct(){
        List<SellerResponseDto> sellerResponseDtos = sellerService.getSellersWithCostliestProduct();
        return new ResponseEntity(sellerResponseDtos,HttpStatus.OK);
    }

    @GetMapping("/get-sellers-with-cheapest-product")
    public ResponseEntity getSellersWithCheapestProduct(){
        List<SellerResponseDto> sellerResponseDtos = sellerService.getSellersWithCheapestProduct();
        return new ResponseEntity(sellerResponseDtos,HttpStatus.OK);
    }
}
