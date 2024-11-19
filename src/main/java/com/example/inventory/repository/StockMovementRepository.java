package com.example.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.model.StockMovement;

public interface StockMovementRepository extends JpaRepository<StockMovement, Long> {

}
