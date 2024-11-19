package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.OrderDTO;
import com.example.inventory.dto.OrderItemDTO;
import com.example.inventory.model.Customer;
import com.example.inventory.model.OrderItem;
import com.example.inventory.model.Orders;
import com.example.inventory.repository.CustomerRepository;
import com.example.inventory.repository.OrderRepository;

import jakarta.persistence.criteria.Order;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private CustomerRepository customerRepository;

	public List<OrderDTO> getAllOrders() {
		List<Orders> allOrders = orderRepository.findAll();
		List<OrderDTO> ordersDTO = allOrders.stream().map(orders -> modelMapper.map(orders, OrderDTO.class))
				.collect(Collectors.toList());
		return ordersDTO;
	}

	public OrderDTO getOrderById(Long id) {
		Orders order = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Orders not found"));
		OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
		return orderDTO;
	}

	public OrderDTO addOrder(OrderDTO orderDTO) {
		Orders order = modelMapper.map(orderDTO, Orders.class);
		orderRepository.save(order);
		OrderDTO savedOrder = modelMapper.map(order, OrderDTO.class);
		return savedOrder;
	}

	public OrderDTO updateOrder(Long id, OrderDTO updatedOrder) {
		Orders order = modelMapper.map(updatedOrder, Orders.class);
		Orders existingOrder = orderRepository.findById(id).orElseThrow(() -> new RuntimeException("Orders not found"));

		Customer customer = null;
		Long customerID = updatedOrder.getCustomerId();
		if (customerID != null) {
			customer = customerRepository.findById(customerID)
					.orElseThrow(() -> new RuntimeException("Customer not found"));
		}

		List<OrderItemDTO> orderItemDTO = updatedOrder.getOrderItems();
		List<OrderItem> orderItems = orderItemDTO.stream().map(orderItem -> modelMapper.map(orderItem, OrderItem.class))
				.collect(Collectors.toList());

		// ADd validation if exists
		existingOrder.setCustomer(customer);
		existingOrder.setOrderDate(updatedOrder.getOrderDate());
		existingOrder.setOrderStatus(updatedOrder.getOrderStatus());
		existingOrder.setShippingMethod(updatedOrder.getShippingMethod());
		existingOrder.setTotalAmount(updatedOrder.getTotalAmount());
		existingOrder.setPaymentStatus(updatedOrder.getPaymentStatus());
		existingOrder.setShippingAddress(updatedOrder.getShippingAddress());
		existingOrder.setInvoiceNumber(updatedOrder.getInvoiceNumber());
		existingOrder.setOrderItems(orderItems);

		orderRepository.save(existingOrder);
		OrderDTO updatedOrderDTO = modelMapper.map(existingOrder, OrderDTO.class);
		return updatedOrderDTO;
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}

}
