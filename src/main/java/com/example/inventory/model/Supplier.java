package com.example.inventory.model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Supplier {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long supplierId;

	@Column(nullable = false)
	private String name;

	private String contactInfo;

	private String address;

	private String paymentTerms; // e.g., net 30, upfront payment, etc.

	private String productCategories;

	private Integer leadTime; // Average delivery time (in days

	private String discountRates;

	private String status;

	@OneToMany(mappedBy = "supplier")
	private List<Product> products; // List of products supplied by this supplier

	@OneToMany(mappedBy = "supplier")
	Product product;

}
