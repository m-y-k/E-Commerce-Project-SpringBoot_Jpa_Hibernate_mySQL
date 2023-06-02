package com.example.Caravan.Repository;

import com.example.Caravan.Model.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<Card,Integer> {
    Card findByCardNo(String cardNo);
}
