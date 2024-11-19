package com.example.inventory.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Orders;
import com.example.inventory.repository.OrderRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	public Optional<Orders> getOrderById(Long id) {
		return orderRepository.findById(id);
	}

	public Orders addOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders updateOrder(Long id, Orders updatedOrder) {
		Orders existingOrder = orderRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("Orders not found"));

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
