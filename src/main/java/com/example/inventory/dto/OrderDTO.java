package com.example.inventory.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDTO {
	private Long orderId;
    private Long customerId;
    private LocalDateTime orderDate;
    private String orderStatus;
    private String shippingMethod;
    private double totalAmount;
    private String paymentStatus;
    private String shippingAddress;
    private String invoiceNumber;
    private List<OrderItemDTO> orderItems;
}
