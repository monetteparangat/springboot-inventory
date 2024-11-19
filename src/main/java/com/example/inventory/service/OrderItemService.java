package com.example.inventory.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.inventory.dto.OrderItemDTO;
import com.example.inventory.model.OrderItem;
import com.example.inventory.model.Product;
import com.example.inventory.repository.OrderItemRepository;
import com.example.inventory.repository.ProductRepository;

@Service
public class OrderItemService {

	@Autowired
	private OrderItemRepository orderItemRepository;
	@Autowired
	private ModelMapper modelMapper;
	@Autowired
	private ProductRepository productRepository;

	public List<OrderItemDTO> getAllOrderItems() {
		List<OrderItem> orderItems = orderItemRepository.findAll();

		List<OrderItemDTO> orderItemDTO = orderItems.stream().map(order -> modelMapper.map(order, OrderItemDTO.class))
				.collect(Collectors.toList());
		return orderItemDTO;
	}

	public OrderItemDTO getOrderItemById(Long id) {
		OrderItem orderItem = orderItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("OrderItem not found"));
		OrderItemDTO orderItemDTO = modelMapper.map(orderItem, OrderItemDTO.class);
		return orderItemDTO;
	}

	public OrderItemDTO addOrderItem(OrderItemDTO orderItemDTO) {
		OrderItem orderItem = modelMapper.map(orderItemDTO, OrderItem.class);
		OrderItem savedOrderItem = orderItemRepository.save(orderItem);
		OrderItemDTO savedOrderItemDTO = modelMapper.map(savedOrderItem, OrderItemDTO.class);
		return savedOrderItemDTO;
	}

	public OrderItemDTO updateOrderItem(Long id, OrderItemDTO updatedOrderItem) {
		OrderItem existingOrderItem = orderItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("OrderItem not found"));

		Long productId = updatedOrderItem.getProductId();
		Product product = null;
		if (productId != null) {
			product = productRepository.findById(productId)
					.orElseThrow(() -> new RuntimeException("Product not found"));
			;
		}

		existingOrderItem.setProduct(product);
		existingOrderItem.setQuantity(updatedOrderItem.getQuantityOrdered());
		existingOrderItem.setPricePerUnit(updatedOrderItem.getPrice());

		orderItemRepository.save(existingOrderItem);
		OrderItemDTO updatedOrderItemDTO = modelMapper.map(existingOrderItem, OrderItemDTO.class);
		return updatedOrderItemDTO;
	}

	public void deleteOrderItem(Long id) {
		OrderItem orderItem = orderItemRepository.findById(id)
				.orElseThrow(() -> new RuntimeException("OrderItem not found"));
		orderItemRepository.delete(orderItem);
	}
}
