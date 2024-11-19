package com.example.inventory.dto;

import lombok.Data;

@Data
public class SupplierDTO {
	private Long id;
	private String name;
	private String contactInfo;
	private String address;
	private String paymentTerms;
	private String productCategories;
	private int leadTime;
	private double discountRates;
	private String status;
}
