package com.example.Caravan.Repository;

import com.example.Caravan.Model.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderEntity,Integer> {
}
