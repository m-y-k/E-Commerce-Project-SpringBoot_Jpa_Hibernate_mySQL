package com.example.Caravan.Controller;

import com.example.Caravan.DTO.RequestDTO.ProductRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.Enum.Category;
import com.example.Caravan.Enum.ProductStatus;
import com.example.Caravan.Exception.CategoryNotFoundException;
import com.example.Caravan.Exception.InvalidProductStatusException;
import com.example.Caravan.Exception.SellerNotFoundException;
import com.example.Caravan.Service.ServiceImp.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productService;
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity(productResponseDto, HttpStatus.CREATED);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-products-by-category")
    public ResponseEntity getProductsByCategory(@RequestParam Category category){
        try {
            List<ProductResponseDto> productResponseDtos = productService.getProductsByCategory(category);
            return new ResponseEntity(productResponseDtos, HttpStatus.OK);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-products-by-category-and-price-above-k")
    public ResponseEntity getProductsByCategoryAndPriceAboveK(@RequestParam Category category, @RequestParam Integer K){
        try {
            List<ProductResponseDto> productResponseDtos = productService.getProductsByCategoryAndPriceAboveK(category, K);
            return new ResponseEntity(productResponseDtos, HttpStatus.OK);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-top5-cheapest-products-by-category")
    public ResponseEntity getTop5CheapestProductsByCategory(@RequestParam Category category){
        try {
            List<ProductResponseDto> productResponseDtos = productService.getTop5CheapestProductsByCategory(category);
            return new ResponseEntity(productResponseDtos, HttpStatus.OK);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-top5-costliest-products-by-category")
    public ResponseEntity getTop5CostliestProductsByCategory(@RequestParam Category category){
        try {
            List<ProductResponseDto> productResponseDtos = productService.getTop5CostliestProductsByCategory(category);
            return new ResponseEntity(productResponseDtos, HttpStatus.OK);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/get-products-by-category-and-status")
    public ResponseEntity getProductsByCategoryAndStatus(@RequestParam Category category, @RequestParam ProductStatus status){
        try {
            List<ProductResponseDto> productResponseDtos = productService.getProductsByCategoryAndStatus(category, status);
            return new ResponseEntity(productResponseDtos, HttpStatus.OK);
        }
        catch(CategoryNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
        catch(InvalidProductStatusException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.NOT_FOUND);
        }
    }
}
