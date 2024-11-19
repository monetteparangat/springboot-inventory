package com.example.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
