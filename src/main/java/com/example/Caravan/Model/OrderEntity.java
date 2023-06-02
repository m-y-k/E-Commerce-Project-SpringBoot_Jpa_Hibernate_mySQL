package com.example.Caravan.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "order_info")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "order_no", unique = true, nullable = false)
    String orderNo;

    @Column(name = "total_value")
    Integer totalValue;

    @CreationTimestamp
    @Column(name = "order_date")
    Date orderDate;

    @Column(name = "card_used")
    String cardUsed;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
