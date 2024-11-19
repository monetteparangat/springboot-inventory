package com.example.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.inventory.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {


}
