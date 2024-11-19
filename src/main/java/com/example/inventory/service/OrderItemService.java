package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.OrderItem;
import com.example.inventory.repository.OrderItemRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;

	public List<OrderItem> getAllOrderItems() {
		return orderItemRepository.findAll();
	}

	public OrderItem getOrderItemById(Long id) {
		return orderItemRepository.findById(id);
	}

	public OrderItem addOrderItem(OrderItem orderItem) {
		return orderItemRepository.save(orderItem);
	}

	public OrderItem updateOrderItem(Long id, OrderItem updatedOrderItem) {
		OrderItem existingOrderItem = orderItemRepository.findById(id);

		// Validation if id exists
		existingOrderItem.setOrder(updatedOrderItem.getOrder());
		existingOrderItem.setProduct(updatedOrderItem.getProduct());
		existingOrderItem.setQuantity(updatedOrderItem.getQuantity());
		existingOrderItem.setPricePerUnit(updatedOrderItem.getPricePerUnit());
		existingOrderItem.setTotalPrice(updatedOrderItem.getTotalPrice());

		return orderItemRepository.save(existingOrderItem);
	}

}
