package com.example.Caravan.Model;

import com.example.Caravan.Enum.Gender;
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
@Table(name = "customer")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "name")
    String name;

    @Column(name = "mobile_no", unique = true, nullable = false)
    String mobNo;

    @Column(name = "age")
    Integer age;

    @Column(name = "email_id", unique = true, nullable = false)
    String emailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    Gender gender;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cards= new ArrayList<>();

    @OneToOne(mappedBy = "customer", cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    List<OrderEntity> orders = new ArrayList<>();
}
