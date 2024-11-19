package com.example.inventory.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDTO {
	private Long customerId;
	private String name;
	private String email;
	private String phoneNumber;
	private String billingAddress;
	private String shippingAddress;
	private String accountType;
}
