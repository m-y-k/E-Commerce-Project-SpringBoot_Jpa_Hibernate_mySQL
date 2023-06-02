package com.example.Caravan.Model;

import com.example.Caravan.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "card")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Integer id;

    @Column(name = "card_no", unique = true, nullable = false)
    String cardNo;

    @Column(name = "cvv", nullable = false)
    Integer cvv;

    @Column(name = "valid_till", nullable = false)
    Date validTill;

    @Enumerated(EnumType.STRING)
    @Column(name = "cardtype")
    CardType cardType;

    @ManyToOne
    @JoinColumn
    Customer customer;
}
