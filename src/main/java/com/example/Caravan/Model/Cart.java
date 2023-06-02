package com.example.Caravan.Model;

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
@Table(name = "cart")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "card_total")
    Integer cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
