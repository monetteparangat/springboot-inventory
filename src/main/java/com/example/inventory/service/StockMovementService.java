package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.StockMovementDTO;
import com.example.inventory.model.Product;
import com.example.inventory.model.StockMovement;
import com.example.inventory.repository.ProductRepository;
import com.example.inventory.repository.StockMovementRepository;

@Service
public class StockMovementService {

	@Autowired
	private StockMovementRepository stockMovementRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductRepository productRepository;

	public List<StockMovementDTO> getAllStockMovements() {
		List<StockMovement> stockMovements = stockMovementRepository.findAll();
		List<StockMovementDTO> stockMovementDTO = stockMovements.stream()
				.map(stockMovement -> modelMapper.map(stockMovement, StockMovementDTO.class))
				.collect(Collectors.toList());
		return stockMovementDTO;
	}

	public StockMovementDTO getStockMovementById(Long id) {
		StockMovement stockMovement = stockMovementRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock movement not found"));
		StockMovementDTO stockMovementDTO = modelMapper.map(stockMovement, StockMovementDTO.class);
		return stockMovementDTO;
	}

	public StockMovementDTO addStockMovement(StockMovementDTO stockMovementDTO) {
		StockMovement stockMovement = modelMapper.map(stockMovementDTO, StockMovement.class);
		stockMovement = stockMovementRepository.save(stockMovement);
		StockMovementDTO savedStockMovementDTO = modelMapper.map(stockMovement, StockMovementDTO.class);
		return savedStockMovementDTO;
	}

	public StockMovementDTO updateStockMovement(Long id, StockMovementDTO stockMovementDTO) {
		StockMovement existingStockMovement = stockMovementRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("StockMovement not found"));

		Long productId = stockMovementDTO.getProductId();
		Product product = null;

		if (productId != null) {
			product = productRepository.findById(productId)
					.orElseThrow(() -> new RuntimeException("StockMovement not found"));
		}
		// validate of existing
		existingStockMovement.setProduct(product);
		existingStockMovement.setTransactionType(stockMovementDTO.getTransactionType());
		existingStockMovement.setQuantityChanged(stockMovementDTO.getQuantityChanged());
		existingStockMovement.setDate(stockMovementDTO.getDate());
		existingStockMovement.setPrice(stockMovementDTO.getPrice());
		existingStockMovement.setReason(stockMovementDTO.getReason());
		existingStockMovement.setReferenceNumber(stockMovementDTO.getReferenceNumber());

		existingStockMovement = stockMovementRepository.save(existingStockMovement);
		StockMovementDTO savedStockMovementDTO = modelMapper.map(existingStockMovement, StockMovementDTO.class);
		return savedStockMovementDTO;
	}

	public void deleteStockMovementById(Long id) {
		StockMovement stockMovement = stockMovementRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Stock movement not found"));
		stockMovementRepository.delete(stockMovement);
	}

}
