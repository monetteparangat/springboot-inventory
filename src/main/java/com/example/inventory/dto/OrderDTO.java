package com.example.inventory.dto;

import java.time.LocalDate;
import java.util.List;

import lombok.Data;

@Data
public class OrderDTO {
	private Long orderId;
    private Long customerId;
    private LocalDate orderDate;
    private String orderStatus;
    private String shippingMethod;
    private double totalAmount;
    private String paymentStatus;
    private String shippingAddress;
    private String invoiceNumber;
    private List<OrderItemDTO> orderItems;
}
