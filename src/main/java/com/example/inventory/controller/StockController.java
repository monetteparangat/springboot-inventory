package com.example.inventory.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.inventory.dto.StockMovementDTO;
import com.example.inventory.service.StockService;

//StockController.java
@RestController
@RequestMapping("/api/stocks")
public class StockController {

	@Autowired
	private StockService stockService;

	@GetMapping("/movements")
	public List<StockMovementDTO> getAllStockMovements() {
		return stockService.getAllStockMovements();
	}

	@GetMapping("/movements/{id}")
	public ResponseEntity<StockMovementDTO> getStockMovementById(@PathVariable Long id) {
		StockMovementDTO stockMovement = stockService.getStockMovementById(id);
		return ResponseEntity.ok(stockMovement);
	}

	@PostMapping("/movements")
	public ResponseEntity<StockMovementDTO> createStockMovement(@RequestBody StockMovementDTO stockMovementDTO) {
		StockMovementDTO stockMovement = stockService.addStockMovement(stockMovementDTO);
		return ResponseEntity.status(201).body(stockMovement);
	}

	@PutMapping("/movements/{id}")
	public ResponseEntity<StockMovementDTO> updateStockMovement(@PathVariable Long id,
			@RequestBody StockMovementDTO stockMovementDTO) {
		StockMovementDTO updatedStockMovement = stockService.updateStockMovement(id, stockMovementDTO);
		return ResponseEntity.ok(updatedStockMovement);
	}

	@DeleteMapping("/movements/{id}")
	public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
		stockService.deleteStockMovement(id);
		return ResponseEntity.noContent().build();
	}
}
