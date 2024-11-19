package com.example.inventory.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import com.example.inventory.model.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

}
