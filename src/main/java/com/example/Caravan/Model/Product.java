package com.example.Caravan.Model;

import com.example.Caravan.Enum.Category;
import com.example.Caravan.Enum.ProductStatus;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "product")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "category")
    Category category;

    @Column(name = "price", nullable = false)
    Integer price;

    @Column(name = "quantity", nullable = false)
    Integer quantity;

    @Enumerated(EnumType.STRING)
    @Column(name = "product_status", nullable = false)
    ProductStatus status;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
