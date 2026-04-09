package com.example.SpringEcom.repo;

import com.example.SpringEcom.model.Order;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepo extends JpaRepository<Order, Long>{
    Optional<Order> findByOrderId(String orderId);
}
