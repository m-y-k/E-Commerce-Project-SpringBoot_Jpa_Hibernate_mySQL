package com.example.Caravan.Service.ServiceImp;

import com.example.Caravan.DTO.RequestDTO.SellerRequestDto;
import com.example.Caravan.DTO.ResponseDTO.ProductResponseDto;
import com.example.Caravan.DTO.ResponseDTO.SellerResponseDto;
import com.example.Caravan.Enum.Category;
import com.example.Caravan.Exception.CategoryNotFoundException;
import com.example.Caravan.Exception.SellerNotFoundException;
import com.example.Caravan.Model.Pair;
import com.example.Caravan.Model.Product;
import com.example.Caravan.Model.Seller;
import com.example.Caravan.Repository.ProductRepository;
import com.example.Caravan.Repository.SellerRepository;
import com.example.Caravan.Service.Interface.SellerServiceInt;
import com.example.Caravan.Transformer.ProductTransformer;
import com.example.Caravan.Transformer.SellerTransformer;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class SellerServiceImpl implements SellerServiceInt {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;

    public SellerResponseDto addSeller( SellerRequestDto sellerRequestDto){
        //RequestDto -> Entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        //save the Entity
        Seller savedSeller = sellerRepository.save(seller);
        //Entity -> ResponseDto
        return SellerTransformer.sellerToSellerResponseDto(savedSeller);
    }

    public String modifyBy(String emailId, String newEmailId) throws SellerNotFoundException{
        Seller seller = sellerRepository.findByEmailId(emailId);

        if(Objects.isNull(seller))
            throw new SellerNotFoundException();

        seller.setEmailId(newEmailId);
        sellerRepository.save(seller);
        return "Email ID updated!";
    }

    public List<SellerResponseDto> getProductCategorySellers(Category category){
        List<Category> categories = List.of(Category.values());
        if(!categories.contains(category))
            throw new CategoryNotFoundException("Category does not exist.");

        List<Product> products = productRepository.findByCategory(category);
        List<SellerResponseDto> sellers = new ArrayList<>();

        for(Product product : products){
            Seller seller = product.getSeller();
            if(!sellers.contains(seller)){
                SellerResponseDto sellerResponseDto = SellerTransformer.sellerToSellerResponseDto(seller);
                sellers.add(sellerResponseDto);
            }

        }
        return sellers;
    }

    public List<ProductResponseDto> getProductsBySellerAndCategory(String sellerEmailId, Category productCategory) throws SellerNotFoundException,CategoryNotFoundException{
        Seller seller = sellerRepository.findByEmailId(sellerEmailId);
        if(Objects.isNull(seller))
            throw new SellerNotFoundException();

        List<Category> categories = List.of(Category.values());
        if(!categories.contains(productCategory))
            throw new CategoryNotFoundException("Category does not exist.");

        List<ProductResponseDto> products = new ArrayList<>();
        for(Product product : seller.getProducts()){
            if(product.getCategory() == productCategory) {
                ProductResponseDto productResponseDto = ProductTransformer.ProductToProductResponseDto(product);
                products.add(productResponseDto);
            }
        }
        return products;
    }

    public List<SellerResponseDto> getSellersWithMostProducts(){
        List<Seller> sellers = sellerRepository.findAll();
        Integer MostNoOfProducts = 0;
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            if(products.size() > MostNoOfProducts){
                MostNoOfProducts = products.size();
            }
        }

        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            if(products.size() == MostNoOfProducts){
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtos;
    }

    public List<SellerResponseDto> getSellersWithLeastProducts() {
        List<Seller> sellers = sellerRepository.findAll();
        Integer LeastNoOfProducts = Integer.MAX_VALUE;
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();

        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            if(products.size() < LeastNoOfProducts){
                LeastNoOfProducts = products.size();
            }
        }


        for(Seller seller : sellers){
            List<Product> products = seller.getProducts();
            if(products.size() == LeastNoOfProducts){
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtos;
    }

    public List<SellerResponseDto> getSellersWithCostliestProduct() {
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        List<Pair> pairOfSellerAndCostliestProduct = new ArrayList<>();
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            Integer cost = 0;
            List<Product> products = seller.getProducts();
            for(Product product : products){
                if(product.getPrice() > cost){
                    cost = product.getPrice();
                }
            }
            pairOfSellerAndCostliestProduct.add(new Pair(cost,seller));
        }

        Integer maxCost = 0;
        for(Pair pair : pairOfSellerAndCostliestProduct){
            Integer price = pair.getPrice();
            if(maxCost < price){
                maxCost = price;
            }
        }

        for(Pair pair : pairOfSellerAndCostliestProduct){
            Integer price = pair.getPrice();
            Seller seller = pair.getSeller();
            if(maxCost == price) {
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtos;
    }

    public List<SellerResponseDto> getSellersWithCheapestProduct() {
        List<SellerResponseDto> sellerResponseDtos = new ArrayList<>();
        List<Pair> pairOfSellerAndCheapestProduct = new ArrayList<>();
        List<Seller> sellers = sellerRepository.findAll();

        for(Seller seller : sellers){
            Integer cost = Integer.MAX_VALUE;
            List<Product> products = seller.getProducts();
            for(Product product : products){
                if(product.getPrice() < cost){
                    cost = product.getPrice();
                }
            }
            pairOfSellerAndCheapestProduct.add(new Pair(cost,seller));
        }

        Integer minCost = Integer.MAX_VALUE;
        for(Pair pair : pairOfSellerAndCheapestProduct){
            Integer price = pair.getPrice();
            if(minCost > price){
                minCost = price;
            }
        }

        for(Pair pair : pairOfSellerAndCheapestProduct){
            Integer price = pair.getPrice();
            Seller seller = pair.getSeller();
            if(minCost == price) {
                sellerResponseDtos.add(SellerTransformer.sellerToSellerResponseDto(seller));
            }
        }
        return sellerResponseDtos;
    }
}
