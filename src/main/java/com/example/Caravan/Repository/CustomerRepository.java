package com.example.Caravan.Repository;

import com.example.Caravan.Model.Customer;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    Customer findByEmailId(String customerEmailId);
   @Query(value = "Select * from customer where gender = :gender AND age between :x AND :y",nativeQuery = true)
    List<Customer> findByGenderAndAgeBetween(String gender, Integer x, Integer y);
}
