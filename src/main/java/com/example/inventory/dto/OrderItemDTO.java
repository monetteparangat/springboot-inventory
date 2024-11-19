package com.example.inventory.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
	private Long orderItemId;
	private Long productId;
	private int quantityOrdered;
	private double price;
}
