package com.example.inventory.service;

import com.example.inventory.model.StockMovement;
import com.example.inventory.repository.StockMovementRepository;
import com.example.inventory.dto.StockMovementDTO;
import com.example.inventory.model.Product;
import com.example.inventory.repository.ProductRepository;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StockService {

	@Autowired
	private StockMovementRepository stockMovementRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	public List<StockMovementDTO> getAllStockMovements() {
		List<StockMovement> stocks = stockMovementRepository.findAll();
		List<StockMovementDTO> stockMovementDTO = stocks.stream()
				.map(stock -> modelMapper.map(stock, StockMovementDTO.class)).collect(Collectors.toList());
		return stockMovementDTO;
	}

	public StockMovementDTO getStockMovementById(Long stockMovementId) {
		StockMovement stockMovement = stockMovementRepository.findById(stockMovementId)
				.orElseThrow(() -> new RuntimeException("StockMovement not found"));
		StockMovementDTO stockMovementDTO = modelMapper.map(stockMovement, StockMovementDTO.class);
		return stockMovementDTO;
	}

	public StockMovementDTO addStockMovement(StockMovementDTO stockMovementDTO) {

		StockMovement stockMovement = modelMapper.map(stockMovementDTO, StockMovement.class);
		stockMovement.setProduct(productRepository.findById(stockMovementDTO.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found")));

		StockMovement savedStockMovement = stockMovementRepository.save(stockMovement);
		return modelMapper.map(savedStockMovement, StockMovementDTO.class);

	}

	public StockMovementDTO updateStockMovement(Long id, StockMovementDTO stockMovementDTO) {
		StockMovement existingStockMovement = stockMovementRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("StockMovement not found"));

		existingStockMovement.setProduct(productRepository.findById(stockMovementDTO.getProductId())
				.orElseThrow(() -> new RuntimeException("Product not found")));
		existingStockMovement.setQuantityChanged(stockMovementDTO.getQuantityChanged());
		existingStockMovement.setDate(stockMovementDTO.getDate());

		StockMovement updatedStockMovement = stockMovementRepository.save(existingStockMovement);
		return modelMapper.map(updatedStockMovement, StockMovementDTO.class);
	}

	public void deleteStockMovement(Long stockMovementId) {
		if (!stockMovementRepository.existsById(stockMovementId)) {
			throw new RuntimeException("StockMovement not found");
		}
		stockMovementRepository.deleteById(stockMovementId);
	}
}
