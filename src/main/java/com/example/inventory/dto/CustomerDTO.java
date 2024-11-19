package com.example.inventory.dto;

import lombok.Data;

@Data
public class CustomerDTO {
	private Long customerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String billingAddress;
	private String shippingAddress;
	private String accountType;
}
