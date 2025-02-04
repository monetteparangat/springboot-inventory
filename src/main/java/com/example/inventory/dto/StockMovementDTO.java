package com.example.inventory.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StockMovementDTO {
	private Long id;
	private Long productId; // Referring to the Product by ID
	private String transactionType;
	private int quantityChanged;
	private LocalDateTime date;
	private double price;
	private String reason;
	private String referenceNumber;
}
