package com.example.inventory.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;
	private String name;
	private String description;
	private String category;
	private String sku;
	private String brand;
	private int quantity;
	private double unitPrice;
	private double sellingPrice;
	private int reorderLevel;
	private Long supplierId;
	private Long categoryId;
	private String barcode;
	private String location;
	private LocalDateTime dateAdded;
	private LocalDateTime lastUpdated;

}
