package com.example.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.StockMovement;
import com.example.inventory.repository.StockMovementRepository;

@Service
public class StockMovementService {

	@Autowired
	private StockMovementRepository stockMovementRepository;

	public List<StockMovement> getAllStockMovements() {
		return stockMovementRepository.findAll();
	}

	public Optional<StockMovement> getStockMovementById(Long id) {
		return stockMovementRepository.findById(id);
	}

	public StockMovement addStockMovement(StockMovement stockMovement) {
		return stockMovementRepository.save(stockMovement);
	}

	public StockMovement updateStockMovement(Long id, StockMovement updatedStockMovement) {
		StockMovement existingStockMovement = stockMovementRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("StockMovement not found"));

		// validate of existing
		existingStockMovement.setProduct(updatedStockMovement.getProduct());
		existingStockMovement.setTransactionType(updatedStockMovement.getTransactionType());
		existingStockMovement.setQuantityChanged(updatedStockMovement.getQuantityChanged());
		existingStockMovement.setDate(updatedStockMovement.getDate());
		existingStockMovement.setPrice(updatedStockMovement.getPrice());
		existingStockMovement.setReason(updatedStockMovement.getReason());
		existingStockMovement.setReferenceNumber(updatedStockMovement.getReferenceNumber());

		return stockMovementRepository.save(existingStockMovement);
	}

	public void deleteStockMovementById(Long id) {
		stockMovementRepository.deleteById(id);
	}

}
