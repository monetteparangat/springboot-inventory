package com.example.inventory.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;

	private String phoneNumber;

	private String billingAddress;

	private String shippingAddress;

	private String accountType; // e.g., "regular", "wholesale"

	@OneToMany(mappedBy = "customer")
	private List<Orders> orders; // List of orders placed by the customer
}
