package com.example.inventory.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.model.Orders;
import com.example.inventory.repository.OrdersRepository;

@Service
public class OrdersService {

	@Autowired
	private OrdersRepository orderRepository;

	public List<Orders> getAllOrders() {
		return orderRepository.findAll();
	}

	public Orders getOrderById(Long orderId) {
		return orderRepository.findById(orderId);
	}

	public Orders addOrder(Orders order) {
		return orderRepository.save(order);
	}

	public Orders updateOrder(Long orderId, Orders updatedOrder) {
		Orders existingOrder = orderRepository.findById(orderId);

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

	public void deleteOrder(Long orderId) {
		orderRepository.deleteById(orderId);
	}
}
