package com.example.inventory.service;

import com.example.inventory.model.StockMovement;
import com.example.inventory.repository.StockMovementRepository;
import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StockService {

	@Autowired
	private StockMovementRepository stockMovementRepository;

	@Autowired
	private ProductRepository productRepository;

	public List<StockMovement> getAllStockMovements() {
		return stockMovementRepository.findAll();
	}

	public StockMovement getStockMovementById(Long stockMovementId) {
		return stockMovementRepository.findById(stockMovementId);
	}

	public StockMovement addStockMovement(StockMovement stockMovement) {
		// Adjust product stock based on movement type
		Optional<Product> productOpt = Optional.of(productRepository.findById(stockMovement.getProduct().getProductId()));
		if (productOpt.isPresent()) {
			Product product = productOpt.get();
			switch (stockMovement.getTransactionType()) {
			case "purchase":
				product.setQuantity(product.getQuantity() + stockMovement.getQuantityChanged());
				break;
			case "sale":
				product.setQuantity(product.getQuantity() - stockMovement.getQuantityChanged());
				break;
			case "adjustment":
				product.setQuantity(product.getQuantity() + stockMovement.getQuantityChanged());
				break;
			default:
				throw new IllegalArgumentException("Invalid transaction type");
			}
			productRepository.save(product);
		}

		return stockMovementRepository.save(stockMovement);
	}

	public void deleteStockMovement(Long stockMovementId) {
		stockMovementRepository.deleteById(stockMovementId);
	}
}
