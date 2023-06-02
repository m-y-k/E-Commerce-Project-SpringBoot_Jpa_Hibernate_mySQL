package com.example.Caravan.Repository;

import com.example.Caravan.Enum.Category;
import com.example.Caravan.Enum.ProductStatus;
import com.example.Caravan.Model.Product;
import com.example.Caravan.Model.Seller;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    List<Product> findByCategory(Category category);

    @Query(value = "select * from product p where p.category = :category and p.price > :k",nativeQuery = true)
    List<Product> findByCategoryAndPriceAboveK(String category, Integer k);

    @Query(value = "select * from product where category = :category order by price asc limit 5",nativeQuery = true)
    List<Product> findTop5CheapestProductByCategory(String category);

    @Query(value = "select * from product where category = :category order by price desc limit 5",nativeQuery = true)
    List<Product> findTop5CostliestProductsByCategory(String category);

    List<Product> findByCategoryAndStatus(Category category, ProductStatus status);
}
