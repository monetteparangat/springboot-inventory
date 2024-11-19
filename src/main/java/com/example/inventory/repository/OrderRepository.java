package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.model.Orders;

public interface OrderRepository extends JpaRepository<Orders, Long> {

}
