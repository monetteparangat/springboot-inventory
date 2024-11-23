package com.example.inventory.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productId;

	@Column(nullable = false)
	private String name;

	@Column(length = 500)
	private String description;

	private String category;

	@Column(unique = true, nullable = false)
	private String sku;

	private String brand;

	@Column(nullable = false)
	private Integer quantity;

	@Column(nullable = false)
	private Double unitPrice;

	@Column(nullable = false)
	private Double sellingPrice;

	private Integer reorderLevel;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier supplier;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private ProductCategory productCategory;

	private String barcode;

	private String location;

	private LocalDateTime dateAdded;

	private LocalDateTime lastUpdated;

	// PrePersist and PreUpdate methods to manage timestamps
	@PrePersist
	protected void onCreate() {
		dateAdded = LocalDateTime.now();
		lastUpdated = LocalDateTime.now();
	}

	@PreUpdate
	protected void onUpdate() {
		lastUpdated = LocalDateTime.now();
	}

}
