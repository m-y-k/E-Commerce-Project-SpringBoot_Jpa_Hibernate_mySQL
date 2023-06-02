package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.ProductRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.Enum.Category;
import com.example.Caravan.Enum.ProductStatus;
import com.example.Caravan.Exception.CategoryNotFoundException;
import com.example.Caravan.Exception.InvalidProductStatusException;
import com.example.Caravan.Exception.SellerNotFoundException;
import com.example.Caravan.Model.Product;
import com.example.Caravan.Model.Seller;
import com.example.Caravan.Repository.ProductRepository;
import com.example.Caravan.Repository.SellerRepository;
import com.example.Caravan.Service.Interface.ProductServiceInt;
import com.example.Caravan.Transformer.ProductTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductServiceInt {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        //RequestDto to Entity
        Seller seller = sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(Objects.isNull(seller))
            throw new SellerNotFoundException();

        List<Product> sellerProducts = seller.getProducts();
        Optional<Product> optionalProduct = isPresent(sellerProducts, productRequestDto.getName(),productRequestDto.getCategory());

        Product product;
        if(optionalProduct.isPresent()){
            product = optionalProduct.get();
            product.setQuantity(product.getQuantity()+productRequestDto.getQuantity());
        }
        else {
            product = ProductTransformer.ProductRequestDtoToProduct(productRequestDto);
            product.setSeller(seller);
            seller.getProducts().add(product);
        }

        //save the Entity
        Seller savedSeller = sellerRepository.save(seller); // saves both seller and product.

        //Entity to ResponseDto
        return ProductTransformer.ProductToProductResponseDto(product);
    }

    public Optional<Product> isPresent(List<Product> products, String productName, Category category){
        for(Product product : products){
            if(product.getName().equalsIgnoreCase(productName) && category == product.getCategory())
                return Optional.of(product);
        }
        return Optional.empty();
    }

    public List<ProductResponseDto> getProductsByCategory(Category category) throws CategoryNotFoundException {
        List<Category> categories = List.of(Category.values());
        if(!categories.contains(category))
            throw new CategoryNotFoundException("Category does not exist.");

        List<Product> products = productRepository.findByCategory(category);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> getProductsByCategoryAndPriceAboveK(Category category, Integer k)throws CategoryNotFoundException{
        List<Category> categories = List.of(Category.values());
        if(!categories.contains(category))
            throw new CategoryNotFoundException("Category does not exist.");

        List<Product> products = productRepository.findByCategoryAndPriceAboveK(String.valueOf(category), k);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> getTop5CheapestProductsByCategory(Category category) throws CategoryNotFoundException{
        List<Category> categories = List.of(Category.values());
        if(!categories.contains(category))
            throw new CategoryNotFoundException("Category does not exist.");

        List<Product> products = productRepository.findTop5CheapestProductByCategory(String.valueOf(category));
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> getTop5CostliestProductsByCategory(Category category) throws CategoryNotFoundException{
        List<Category> categories = List.of(Category.values());
        if(!categories.contains(category))
            throw new CategoryNotFoundException("Category does not exist.");

        List<Product> products = productRepository.findTop5CostliestProductsByCategory(String.valueOf(category));
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }

    public List<ProductResponseDto> getProductsByCategoryAndStatus(Category category, ProductStatus status) throws CategoryNotFoundException,InvalidProductStatusException{
        List<Category> categories = List.of(Category.values());
        if(!categories.contains(category))
            throw new CategoryNotFoundException("Category does not exist.");

        List<ProductStatus> productStatuses = List.of(ProductStatus.values());
        if(!productStatuses.contains(status))
            throw new InvalidProductStatusException();

        List<Product> products = productRepository.findByCategoryAndStatus(category, status);
        List<ProductResponseDto> productResponseDtos = new ArrayList<>();

        for(Product product : products){
            ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
            productResponseDtos.add(productResponseDto);
        }
        return productResponseDtos;
    }
}
