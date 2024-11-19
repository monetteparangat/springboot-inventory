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
import com.example.inventory.service.StockMovementService;

@RestController
@RequestMapping
public class StockMovementController {

	@Autowired
	private StockMovementService stockMovementService;

	@GetMapping
	public List<StockMovementDTO> getAllStockMovements() {
		return stockMovementService.getAllStockMovements();
	}

	@GetMapping("/{id}")
	public ResponseEntity<StockMovementDTO> getStockMovementById(@PathVariable Long id) {
		StockMovementDTO stockMovement = stockMovementService.getStockMovementById(id);
		return ResponseEntity.ok(stockMovement);
	}

	@PostMapping
	public ResponseEntity<StockMovementDTO> createStockMovement(@RequestBody StockMovementDTO stockMovementDTO) {
		StockMovementDTO stockMovement = stockMovementService.addStockMovement(stockMovementDTO);
		return ResponseEntity.status(201).body(stockMovement);
	}

	@PutMapping("/{id}")
	public ResponseEntity<StockMovementDTO> updateStockMovement(@PathVariable Long id,
			@RequestBody StockMovementDTO stockMovementDTO) {
		StockMovementDTO stockMovement = stockMovementService.updateStockMovement(id, stockMovementDTO);
		return ResponseEntity.ok(stockMovement);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStockMovement(@PathVariable Long id) {
		stockMovementService.deleteStockMovementById(id);
		return ResponseEntity.noContent().build();
	}

}
