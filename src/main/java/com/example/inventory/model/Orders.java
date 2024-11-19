package com.example.inventory.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
@Table(name = "orders")
public class Orders {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;

	@ManyToOne
	@JoinColumn(name = "customer_id", nullable = false)
	private Customer customer;

	@Column(nullable = false)
	private LocalDateTime orderDate;

	@Column(nullable = false)
	private String orderStatus;

	private String shippingMethod;

	@Column(nullable = false)
	private Double totalAmount;

	@Column(nullable = false)
	private String paymentStatus; // e.g., "paid", "unpaid", "partially paid"

	private String shippingAddress; // If different from billing address

	private String invoiceNumber;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems; // List of items in the order

	@PrePersist
	protected void onCreate() {
		orderDate = LocalDateTime.now();
	}

}
