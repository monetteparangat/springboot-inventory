package com.example.inventory.dto;

import lombok.Data;

@Data
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
	private String barcode;
	private String location;
	private String dateAdded;
	private String lastUpdated;
	
}
