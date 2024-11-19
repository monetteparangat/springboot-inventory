package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Order;
import com.example.inventory.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public Order getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	public Order addOrder(Order order) {
		return orderRepository.save(order);
	}

	public Order updateOrder(Long id, Order updatedOrder) {
		Order existingOrder = orderRepository.findById(id);

		// ADd validation if exists
		existingOrder.setCustomer(updatedOrder.getCustomer());
		existingOrder.setOrderDate(updatedOrder.getOrderDate());
		existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
		existingOrder.setShippingMethod(updatedOrder.getShippingMethod());
		existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
		existingOrder.setPaymentStatus(updatedOrder.getPaymentStatus());
		existingOrder.setShippingAddress(updatedOrder.getShippingAddress());
		existingOrder.setInvoiceNumber(updatedOrder.getInvoiceNumber());
		existingOrder.setOrderItems(updatedOrder.getOrderItems());

		return orderRepository.save(existingOrder);
	}
	
	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

}
